/**
 * 
 */
package gui.helper;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Transferable object, handling the origin of the data.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-21
 */
public class OriginatedTransferable implements Transferable {
	public static final DataFlavor 
							ORIGINATED_DATA_FLAVOR = 
									createConstant(
										OriginatedData.class, 
										"application/x-java-originateddata");
	private OriginatedData origData;
	
	public OriginatedTransferable(OriginatedData origData) {
		this.origData = origData;
	}
	
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		return this.origData;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[]{ORIGINATED_DATA_FLAVOR};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		boolean supported = false;
        for (DataFlavor available : getTransferDataFlavors()) {
            if (available.equals(flavor)) {
                supported = true;
                break;
            }
        }
        return supported;
	}
	
	static protected DataFlavor createConstant(Class<?> clazz, String name) {
        try {
            return new DataFlavor(clazz, name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
