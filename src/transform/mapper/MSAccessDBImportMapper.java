/**
 * Package containing mapper classes, used to map attributes of model classes
 * onto the imported file formats, e.g. Excel columns etc.
 */
package transform.mapper;

/**
 * Mapper for the MS Access databases. Maps basically only the path to the 
 * MS Access file.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class MSAccessDBImportMapper implements Mapper<String, String> {

	String dbPath;
	
	@Override
	public String getMappedObject(String key) {
		// TODO Auto-generated method stub
		return this.dbPath;
	}

	@Override
	public void addMapping(String key, String object) {
		this.dbPath = object;
	}
}
