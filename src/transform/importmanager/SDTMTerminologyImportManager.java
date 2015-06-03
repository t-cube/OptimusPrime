/**
 * Package containing the import manager for various file formats.
 */ 
package transform.importmanager;


import java.util.HashMap;
import java.util.Map;

import transform.mapper.SDTMTerminologyImportMapper;
import transform.models.sdtmterminology.SDTMTerminology;
import transform.models.sdtmterminology.SDTMTerminologyTreeNode;
import transform.models.sdtmterminology.SDTMTerminology.KEYS;
import filewrapper.msexcel.MSExcelWrapper;

/**
 * Import manager for the SDTM Terminology code lists.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SDTMTerminologyImportManager implements 
							ImportManager<Map<String, SDTMTerminologyTreeNode>, 
											SDTMTerminologyImportMapper, 
											MSExcelWrapper> {

	@Override
	public Map<String, SDTMTerminologyTreeNode> importData(
			SDTMTerminologyImportMapper mapper, MSExcelWrapper fileWrapper) {
		
		Map<String, SDTMTerminologyTreeNode> nodes = 
								new HashMap<String, SDTMTerminologyTreeNode>();
		
		while (fileWrapper.hasNext()){
			// first of all get the next line, i.e. the second because the first 
			// contains headlines
			fileWrapper.next();
			
			SDTMTerminology term = new SDTMTerminology();
			
			term.setCode(fileWrapper.getData(
								mapper.getMappedObject(KEYS.CODE)));
			
			term.setCodelistCode(fileWrapper.getData(
								mapper.getMappedObject(KEYS.CODE_LIST_CODE)));
			
			term.setExtensible(Boolean.parseBoolean(fileWrapper.getData(
									mapper.getMappedObject(KEYS.EXTENSIBLE))));
			
			term.setCodelistName(fileWrapper.getData(
								mapper.getMappedObject(KEYS.CODE_LIST_NAME)));
			
			term.setSubmissionValue(fileWrapper.getData(
								mapper.getMappedObject(KEYS.SUBMISSION_VALUE)));
			
			String tempSynonyms = fileWrapper.getData(
								mapper.getMappedObject(KEYS.CDISC_SYNONYMS));
			String[] synonyms = tempSynonyms.split(";");
			for (int i=0; i<synonyms.length; i++){
				synonyms[i] = synonyms[i].replaceFirst("\\s+", "");
			}
			term.setCDISCSynonyms(synonyms);
			
			term.setCDISCDefinition(fileWrapper.getData(
										mapper.getMappedObject(
												KEYS.CDISC_DEFINITION)));
			
			term.setNCIPreferredTerm(fileWrapper.getData(
										mapper.getMappedObject(
												KEYS.NCI_PREFERRED_TERM)));
			
			
			// if it's a codelist term
			String codelistCode = term.getCodelistCode();
			if (codelistCode==null){
				// if it is not already in the map then add it
				codelistCode = term.getCode();
				if (!nodes.containsKey(codelistCode)){
					nodes.put(codelistCode, new SDTMTerminologyTreeNode(term));				
				}else{
					// it is already in the map, that means a child was created 
					// referencing this parent codelist
					// set the children of the already created codelist as my 
					// children, and overwrite the already exisiting codelist
					SDTMTerminologyTreeNode node = 
											new SDTMTerminologyTreeNode(term);
					SDTMTerminologyTreeNode oldNode = nodes.get(codelistCode);
					
					for (int i=0; i<oldNode.getChildCount(); i++){
						node.add((SDTMTerminologyTreeNode)oldNode
																.getChildAt(i));
					}					

					nodes.put(codelistCode, node);
				}
			}else{
				// if it' is a child, check if the parent node already exists
				if (nodes.containsKey(codelistCode)){
					//  if it exists just add the child term node
					SDTMTerminologyTreeNode node = nodes.get(codelistCode);
					node.add (new SDTMTerminologyTreeNode(term));
				}else{
					// if it not exists create a temporary parent node, add the 
					// child node and add everything to the nodes map
					SDTMTerminology tempTerm = new SDTMTerminology();
					term.setCode(codelistCode);

					SDTMTerminologyTreeNode node = 
										new SDTMTerminologyTreeNode(tempTerm);
					node.add(new SDTMTerminologyTreeNode(term));
					
					nodes.put(codelistCode, node);
				}
			}
		}
		
		return nodes;
	}

}
