/**
 * Package containing mapper classes, used to map attributes of model classes
 * onto the imported file formats, e.g. Excel columns etc.
 */
package transform.mapper;

/**
 * Interface for Mapper classes.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-09
 */
public interface Mapper<K, O> {
	
	/**
	 * Adds a mapping to the map.
	 * @param key The key for the object.
	 * @param object The concerning object to map.
	 */
	public void addMapping(K key, O object);
	
	/**
	 * Returns the mapped object for an input key.
	 * @param key The key of which we want the mapped object.
	 * @return The mapped object of the given key.
	 */
	public O getMappedObject(K key);
}
