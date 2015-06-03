/**
 * Package containing the import manager for various file formats.
 */
package transform.importmanager;

import java.util.Map;

import dbconnect.msaccess.TableDefinition;
import filewrapper.msaccess.MSAccessWrapper;
import transform.mapper.MSAccessDBImportMapper;

/**
 * Import manager for a MS Access database. Retrieving all tables of the 
 * database, as there is no more necessary information.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class MSAccessDBImportManager implements 
										ImportManager<
											Map<String, TableDefinition>, 
											MSAccessDBImportMapper, 
											MSAccessWrapper> {
	
	
	@Override
	public Map<String, TableDefinition> importData(
												MSAccessDBImportMapper mapper, 
												MSAccessWrapper fileWrapper) {
		// TODO Auto-generated method stub
		return fileWrapper.getData(mapper.getMappedObject(
												mapper.getMappedObject("")));
	}
	
}
