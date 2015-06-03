/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example.
 */
package transform.models;

import transform.importmanager.ImportManager;
import transform.mapper.Mapper;
import filewrapper.FileWrapper;

/**
 * The import model is an extension of the model interface. It extends the model
 * interface by adding Mapper, FileWrapper and ImportManager, which can be used
 * to import data out of a file into this model.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public interface ImportModel<
							R,
							M extends Mapper<?, ?>, 
							F extends FileWrapper<?, ?>, 
							I extends ImportManager<?,M,F>
							> extends Model<R> {

	/**
	 * @param mapper The Mapper object, which should be set and used by this 
	 * 				model to retrieve columns/rows/etc. of the file for the 
	 * 				attribute of the object stored in the model.
	 */
	public void setMapper(M mapper);
	
	/**
	 * @param fileWrapper The FileWrapper object, which should be set and used
	 * 						by this model to access the file.
	 */
	public void setFileWrapper(F fileWrapper);
	
	/**
	 * @param importManager The ImportManager object, which should be set and 
	 * 						used by this model to import the data of the file
	 * 						into this model.
	 */
	public void setImportManager(I importManager);
	
}
