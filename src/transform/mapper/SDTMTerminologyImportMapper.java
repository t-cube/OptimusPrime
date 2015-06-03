/**
 * Package containing mapper classes, used to map attributes of model classes
 * onto the imported file formats, e.g. Excel columns etc.
 */
package transform.mapper;

import java.io.Serializable;
import java.util.HashMap;

import transform.models.sdtmterminology.SDTMTerminology;
import transform.models.sdtmterminology.SDTMTerminology.KEYS;

/**
 * Class to map SDTM Terminology keys to the columns of an excel file containing
 * these information.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-09
 */
public class SDTMTerminologyImportMapper implements Mapper<KEYS, Integer>, 
													Serializable{
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
	
	private HashMap<SDTMTerminology.KEYS, Integer> columnMap;
	
	/**
	 * Instantiates the class, creates and sets a HashMap to store the mapping.
	 */
	public SDTMTerminologyImportMapper() {
		this.columnMap = new HashMap<SDTMTerminology.KEYS, Integer>();
	}
	
	
	@Override
	public void addMapping(SDTMTerminology.KEYS key, Integer index){
		this.columnMap.put(key, index);
	}

	@Override
	public Integer getMappedObject(KEYS key) {
		Integer index = this.columnMap.get(key); 
		return (index!=null?index.intValue():null);
	}
}
