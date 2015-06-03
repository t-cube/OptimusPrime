/**
 * Package containing the import manager for various file formats.
 */ 
package transform.importmanager;

import java.util.HashMap;
import java.util.Map;

import transform.models.targetstructures.sdtm.SDTMDomain;
import transform.models.targetstructures.sdtm.SDTMDomainRecord;
import transform.mapper.SDTMDomainRecordImportMapper;
import filewrapper.msexcel.MSExcelWrapper;

/**
 * Import manager for the SDTM Domain, used as target structure.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04.17
 */
public class SDTMDomainImportManager implements 
											ImportManager<
												Map<String, SDTMDomain>, 
												SDTMDomainRecordImportMapper, 
												MSExcelWrapper> {

	@Override
	public Map<String, SDTMDomain> importData(
											SDTMDomainRecordImportMapper mapper,
											MSExcelWrapper fileWrapper) {
		int sheetCount = fileWrapper.getSheetCount();
		Map<String, SDTMDomain> result = new HashMap<String, SDTMDomain>();
		
		for (int i=0; i<sheetCount; i++){			
			SDTMDomain domain = new SDTMDomain();		
			fileWrapper.selectSheet(i);
			domain.setDomainName(fileWrapper.getActiveSheetName());
			
			// if the sheet has a next row, step forward, because the current 
			// row is the assumptions headline
			if (fileWrapper.hasNext()){
				fileWrapper.next();
				domain.setAssumption(fileWrapper.getData(
										mapper.getMappedObject(
										SDTMDomainRecord.KEYS.ASSUMPTIONS)));
				
				Map<String, SDTMDomainRecord> records = 
										new HashMap<String, SDTMDomainRecord>();
				
				// The specification column headlines
				fileWrapper.next();
				while (fileWrapper.hasNext()){
					// go one forward because we are in the column headlines
					fileWrapper.next();
					
					SDTMDomainRecord record = new SDTMDomainRecord();
					
					record.setVariableName(
							fileWrapper.getData(
								mapper.getMappedObject(
										SDTMDomainRecord.KEYS.VARIABLE_NAME)));
					record.setVariableLabel(
							fileWrapper.getData(
								mapper.getMappedObject(
										SDTMDomainRecord.KEYS.VARIABLE_LABEL)));
					record.setType(
							fileWrapper.getData(
								mapper.getMappedObject(
										SDTMDomainRecord.KEYS.TYPE)));
					record.setCodeList(
							fileWrapper.getData(
								mapper.getMappedObject(
										SDTMDomainRecord.KEYS.CODE_LIST)));
					record.setRole(
							fileWrapper.getData(
								mapper.getMappedObject(
										SDTMDomainRecord.KEYS.ROLE)));
					record.setCdiscNotes(
							fileWrapper.getData(
								mapper.getMappedObject(
										SDTMDomainRecord.KEYS.CDISC_NOTES)));
					record.setCore(
							fileWrapper.getData(
								mapper.getMappedObject(
										SDTMDomainRecord.KEYS.CORE)));
					
					records.put(record.getVariableName(), record);
				}
				
				domain.setSpecification(records);
			}
			
			result.put(domain.getDomainName(), domain);
		}
		
		return result;
	}

}
