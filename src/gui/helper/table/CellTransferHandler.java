/**
 * 
 */
package gui.helper.table;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import gui.helper.OriginatedData;
import gui.helper.OriginatedTransferable;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import util.DebugSystem;

/**
 * @author TDietl
 *
 */
public class CellTransferHandler extends TransferHandler {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;

	
	
	
	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#canImport(javax.swing.TransferHandler.TransferSupport)
	 */
	@Override
	public boolean canImport(TransferSupport info) {
		// if it is not a string like data type, it can't be imported
		if (!info.isDataFlavorSupported(OriginatedTransferable
												.ORIGINATED_DATA_FLAVOR)){
			return false;
		}
		
		OriginatedData od;
		try {
			od = (OriginatedData) info.getTransferable()
										.getTransferData(
											OriginatedTransferable
												.ORIGINATED_DATA_FLAVOR);
			
			String origin = od.getOrigin();
			
			if (origin.equals("Fields") || origin.equals("Variables")){
				return true;
			}			
		} catch (UnsupportedFlavorException e) {
			DebugSystem.logError(e);
		} catch (IOException e) {
			DebugSystem.logError(e);
		}
		return false;
	}




	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#importData(javax.swing.TransferHandler.TransferSupport)
	 */
	@Override
	public boolean importData(TransferSupport support) {
		try {
			OriginatedData od = (OriginatedData) support.getTransferable().getTransferData(OriginatedTransferable.ORIGINATED_DATA_FLAVOR);
			
			String origin = od.getOrigin();
			
			if (origin.equals("Fields")){
				JTextArea jt = (JTextArea) support.getComponent();
				jt.insert(od.getValue(), jt.getCaretPosition());
			}else{
				JTextField jt = (JTextField) support.getComponent();
				jt.setText(od.getValue());
			}
			
			
		} catch (UnsupportedFlavorException e) {
			DebugSystem.logError(e);
		} catch (IOException e) {
			DebugSystem.logError(e);
		}
		
		
		return true;
	}
	
		
}
