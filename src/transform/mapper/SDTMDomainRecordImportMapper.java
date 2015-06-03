/**
 * Package containing mapper classes, used to map attributes of model classes
 * onto the imported file formats, e.g. Excel columns etc.
 */
package transform.mapper;

import java.util.HashMap;
import java.util.Map;

import transform.models.targetstructures.sdtm.SDTMDomainRecord.KEYS;

/**
 * Mapper for the sdtm specification excel files. Maps the specification column
 * names as keys onto the column indices of the excel file.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SDTMDomainRecordImportMapper implements Mapper<KEYS, Integer> {
	private Map<KEYS, Integer> map;
	
	/**
	 * Simple constructor, only instantiating the map.
	 */
	public SDTMDomainRecordImportMapper(){
		this.map = new HashMap<KEYS, Integer>();
	}

	@Override
	public void addMapping(KEYS key, Integer object) {
		this.map.put(key, object);
	}

	@Override
	public Integer getMappedObject(KEYS key) {		
		return this.map.get(key);
	}

}
