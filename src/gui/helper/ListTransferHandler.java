/**
 * Package containing helper classes for Swing components. 
 */
package gui.helper;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

/**
 * TransferHandler class enabling coping text items out of a list.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class ListTransferHandler extends TransferHandler {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
	
	
    protected Transferable createTransferable(JComponent c) {
        // get the selected list values
        @SuppressWarnings("unchecked")
		JList<String> list = (JList<String>)c;
        Object[] values = list.getSelectedValuesList().toArray();
        
        StringBuffer sb = new StringBuffer();
        
        // and add them into a string with "," as delimiter
        for (int i = 0; i < values.length; i++) {
            Object val = values[i];
            
            sb.append((i==0? "" : ", "));
            sb.append(val == null ? "" : val.toString());
        }
        
        return new OriginatedTransferable(new OriginatedData(sb.toString(), 
    														list.getName()));
    }
	
    
	 /**
     * We support both copy and move actions.
     */
    public int getSourceActions(JComponent c) {
        return TransferHandler.COPY_OR_MOVE;
    }
}
