/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example.
 */
package transform.models;

/**
 * The model interface defining the basic functions for a model object.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public interface Model<R> {
	/**
	 * Refresh the model, i.e. reading out the data from the file or else.
	 */
	public void refresh();
	
	/**
	 * @return The data stored in the model, usually a map object or a list.
	 */
	public R getData();
}
