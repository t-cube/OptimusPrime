/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example. In this case for the source database.
 */
package transform.models.sourcedb;

import java.util.Map;

import dbconnect.msaccess.TableDefinition;
import filewrapper.msaccess.MSAccessWrapper;
import transform.importmanager.MSAccessDBImportManager;
import transform.mapper.MSAccessDBImportMapper;
import transform.models.ImportModel;

/**
 * Model containing all tables of a database, imported using the given import 
 * manager, file wrapper and mapper.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SourceTablesModel implements ImportModel<
												Map<String, TableDefinition>, 
												MSAccessDBImportMapper, 
												MSAccessWrapper, 
												MSAccessDBImportManager> {
	private MSAccessDBImportMapper mapper;
	private MSAccessWrapper fileWrapper;
	private MSAccessDBImportManager importManager;
	private Map<String, TableDefinition> tablesMap;
	
	/**
	 * Simple constructor setting the mapper, file wrapper and import manager.
	 * It will automatically refresh itself and read out the data when first 
	 * created.
	 * @param mapper The mapper for the import used to identify columns, rows,
	 * 				 etc. for given data point keys.
	 * @param fileWrapper The file wrapper used to access the MS Access DB file.
	 * @param importManager The import manager, which imports the data using 
	 * 						file wrapper and mapper.
	 */
	public SourceTablesModel(MSAccessDBImportMapper mapper,
							 MSAccessWrapper fileWrapper, 
							 MSAccessDBImportManager importManager){
		this.mapper = mapper;
		this.fileWrapper = fileWrapper;
		this.importManager = importManager;
		
		refresh();
	}
	
	@Override
	public void refresh() {
		this.tablesMap = this.importManager.importData(this.mapper, 
														this.fileWrapper);
	}

	@Override
	public Map<String, TableDefinition> getData() {
		return this.tablesMap;
	}

	@Override
	public void setMapper(MSAccessDBImportMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void setFileWrapper(MSAccessWrapper fileWrapper) {
		this.fileWrapper = fileWrapper;
	}

	@Override
	public void setImportManager(MSAccessDBImportManager importManager) {
		this.importManager = importManager;
	}

}
