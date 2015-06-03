/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example.
 */
package transform.models.targetstructures.sdtm;

import java.util.Map;

import filewrapper.msexcel.MSExcelWrapper;
import transform.importmanager.SDTMDomainImportManager;
import transform.mapper.SDTMDomainRecordImportMapper;
import transform.models.ImportModel;

/**
 * Import model for a sdtm domain. Importing one sheet of the SDTM specification
 * excel file.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SDTMDomainModel implements 
								ImportModel<
										Map<String, SDTMDomain>, 
										SDTMDomainRecordImportMapper, 
										MSExcelWrapper, 
										SDTMDomainImportManager> {
	
	private SDTMDomainRecordImportMapper mapper;
	private MSExcelWrapper fileWrapper;
	private SDTMDomainImportManager importManager;
	private Map<String, SDTMDomain> sdtmDomains;
	
	
	/**
	 * Simple constructor setting mapper, file wrapper and import manager and
	 * refreshs the model i.e. importing the data.
	 * @param mapper The mapper object used to map the SDTM Domain Keys onto the
	 * 					MS Excel columns.
	 * @param fileWrapper The file wrapper to access the MS Excel file.
	 * @param importManager The import manager, importing the data using the
	 * 						mapper and the file wrapper.
	 */
	public SDTMDomainModel(SDTMDomainRecordImportMapper mapper, 
							MSExcelWrapper fileWrapper, 
							SDTMDomainImportManager importManager){
		this.mapper = mapper;
		this.fileWrapper = fileWrapper;
		this.importManager = importManager;
		
		this.refresh();
	}
	
	@Override
	public void refresh() {
		this.sdtmDomains = this.importManager.importData(this.mapper, 
														 this.fileWrapper);
	}

	@Override
	public Map<String, SDTMDomain> getData() {
		return this.sdtmDomains;
	}

	@Override
	public void setMapper(SDTMDomainRecordImportMapper mapper) {
		this.mapper = mapper;		
	}

	@Override
	public void setFileWrapper(MSExcelWrapper fileWrapper) {
		this.fileWrapper = fileWrapper;
	}

	@Override
	public void setImportManager(SDTMDomainImportManager importManager) {
		this.importManager = importManager;
	}	
	
}
