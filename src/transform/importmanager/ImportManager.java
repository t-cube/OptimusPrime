/**
 * Package containing the import manager for various file formats.
 */
package transform.importmanager;

import filewrapper.FileWrapper;
import transform.mapper.Mapper;

/**
 * Interface defining the minimal functions of an import manager.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public interface ImportManager<R, M extends Mapper<?, ?>, 
								  F extends FileWrapper<?, ?>> {
	
	/**
	 * Function, which uses the mapper and file wrapper to import the data.
	 * @param mapper An object mapping keys, etc. to the file format specific
	 * 					columns, keys, etc. 
	 * @param fileWrapper An object wrapping the file format i.e. allowing the 
	 * 						access to the file.
	 * @return The read out data/data points of the file accessed by the file
	 * 			wrapper. 
	 */
	public R importData(M mapper, F fileWrapper);
	
}
