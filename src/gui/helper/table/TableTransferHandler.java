/**
 * Package containing helper classes for Swing components. In this case helper
 * classes for the JTable component.
 */
package gui.helper.table;

import gui.helper.OriginatedData;
import gui.helper.OriginatedTransferable;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.TransferHandler;

import util.DebugSystem;

/**
 * TransferHandler class enabling dropping text items into the table.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class TableTransferHandler extends TransferHandler {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
		
	public boolean canImport(TransferHandler.TransferSupport info){
		// if it is not a string like data type, it can't be imported
		if (!info.isDataFlavorSupported(OriginatedTransferable
												.ORIGINATED_DATA_FLAVOR)){
			return false;
		}
		
		
		if (info.getComponent() instanceof JTable){
			// get the possible drop location 
			JTable.DropLocation dl = (JTable.DropLocation) 
												info.getDropLocation();
			
			// get the cell in which we would drop
			int column = dl.getColumn();
			int row = dl.getRow();
			
			
			// if it's not the formular column, it can't be imported			
			OriginatedData od;
			try {
				od = (OriginatedData) info.getTransferable()
											.getTransferData(
												OriginatedTransferable
													.ORIGINATED_DATA_FLAVOR);
				
				String origin = od.getOrigin();
				
				if (!((origin.equals("Variables") && column == 0) ||
					(origin.equals("Fields") && column == 2))){
					return false;
				}else{
				
					// if it's a correct cell
					JTable jt = (JTable)info.getComponent();
					// start editing the cell to allow dropping the string into 
					// existing text
					jt.editCellAt(row, column);
				}
				
				return true;
				
			} catch (UnsupportedFlavorException e) {
				DebugSystem.logError(e);
			} catch (IOException e) {
				DebugSystem.logError(e);
			}			
		}
				
		return false;
	}

	
	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#importData(javax.swing.TransferHandler.TransferSupport)
	 */
	/*@Override
	public boolean importData(TransferSupport supp) {
		// TODO Auto-generated method stub
		
		JTable jt = (JTable) supp.getComponent();
		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
		JTable.DropLocation dl = (JTable.DropLocation) supp.getDropLocation();
		int row = dl.getRow();
		int column = dl.getColumn();
		OriginatedData od;
		try {
			od = (OriginatedData)supp.getTransferable().getTransferData(OriginatedTransferable.ORIGINATED_DATA_FLAVOR);
			dtm.setValueAt(od.getValue(),row, column);
			
			return true;
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}	
	*/
	
	
}
