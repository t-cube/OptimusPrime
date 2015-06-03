/**
 * Package contains wrapper for various file formats.
 */
package filewrapper;

/**
 * File wrapper interface. Defines the minimal functions a file wrapper must
 * have to allow retrieving data. 
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public interface FileWrapper<K, O> {
	
	/**
	 * Does the file has a next data point, record, row, etc.  
	 * @return True, if more data can be returned.
	 */
	public boolean hasNext();
	
	/**
	 * Select the next data point, record, row, etc.
	 */
	public void next();
	
	/**
	 * @param key The key, column, etc. under which the data point is stored.
	 * @return Object stored in the file under the given key, column, etc.
	 */
	public O getData(K key);
}
