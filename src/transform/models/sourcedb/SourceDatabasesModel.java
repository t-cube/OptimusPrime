/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example. In this case for the source database.
 */
package transform.models.sourcedb;

import java.util.HashMap;
import java.util.Map;

import filewrapper.msaccess.MSAccessWrapper;
import transform.importmanager.MSAccessDBImportManager;
import transform.mapper.MSAccessDBImportMapper;
import transform.models.Model;

/**
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SourceDatabasesModel implements Model<Map<String, SourceTablesModel>> {
	private Map<String, SourceTablesModel> tablesModels;
	
	/**
	 * Simple constructor instantiating the map for the SourceTablesModel 
	 * objects.
	 */
	public SourceDatabasesModel(){
		this.tablesModels = new HashMap<String, SourceTablesModel>();
	}
	
	/**
	 * Creates a new SourceTablesModel, which imports all tables of the database
	 * given by the file path and stores the model in the map mapped with the
	 * database alias.
	 * @param fileAlias
	 * @param filePath
	 */
	public void addTablesModel(String fileAlias, String filePath){
		MSAccessDBImportManager importManager = new MSAccessDBImportManager();
		MSAccessWrapper fileWrapper = new MSAccessWrapper();
		MSAccessDBImportMapper mapper = new MSAccessDBImportMapper();
		
		mapper.addMapping("File Path", filePath);
		
		SourceTablesModel model = new SourceTablesModel(mapper, fileWrapper, 
														importManager);
		this.tablesModels.put(fileAlias, model);
	}
	
	@Override
	public void refresh() {
		for(SourceTablesModel model : this.tablesModels.values()){
			model.refresh();
		}
	}

	@Override
	public Map<String, SourceTablesModel> getData() {
		return this.tablesModels;
	}
}
