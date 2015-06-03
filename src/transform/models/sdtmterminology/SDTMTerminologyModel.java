/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example. In this case model object for the 
 * SDTM Terminology code lists.
 */
package transform.models.sdtmterminology;

import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import filewrapper.msexcel.MSExcelWrapper;
import transform.importmanager.SDTMTerminologyImportManager;
import transform.mapper.SDTMTerminologyImportMapper;
import transform.models.ImportModel;

/**
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SDTMTerminologyModel implements ImportModel<DefaultMutableTreeNode,
												SDTMTerminologyImportMapper, 
							  					MSExcelWrapper, 
							  					SDTMTerminologyImportManager> {
	private SDTMTerminologyImportManager importManager;
	private SDTMTerminologyImportMapper mapper;
	private MSExcelWrapper fileWrapper;
	private DefaultMutableTreeNode root;
	
	
//- Constructor --------------------------------------------------------------//
	
	public SDTMTerminologyModel(SDTMTerminologyImportMapper mapper, 
								MSExcelWrapper fileWrapper, 
								SDTMTerminologyImportManager importManager) {		
		this.mapper = mapper;
		this.fileWrapper = fileWrapper;
		this.importManager = importManager;
		
		this.refresh();		
	}

	@Override
	public void refresh() {
		this.root = new DefaultMutableTreeNode();
		
		Map<String, SDTMTerminologyTreeNode> nodes;
		
		nodes = this.importManager.importData(this.mapper, this.fileWrapper);		
		for (SDTMTerminologyTreeNode node : nodes.values()){
			this.root.add(node);
		}
	}
	

//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//

	@Override
	public DefaultMutableTreeNode getData() {
		return this.root;
	}
	
	@Override
	public void setImportManager(SDTMTerminologyImportManager importManager) {
		this.importManager = importManager;
	}

	@Override
	public void setMapper(SDTMTerminologyImportMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void setFileWrapper(MSExcelWrapper fileWrapper) {
		this.fileWrapper = fileWrapper;
	}


//- Getters ------------------------------------------------------------------//


//- Setters ------------------------------------------------------------------//

}
