/**
 * Package containing helper classes for Swing components. In this case helper
 * classes for the JTable component.
 */
package gui.helper.table;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.table.TableCellEditor;

/**
 * Cell Editor for the JTable to allow editing the cell in a multi line way.
 * Thank you captain obvious.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class SingleLineCellEditor extends AbstractCellEditor 
								 implements TableCellEditor {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
	
	//The actual text area component doing the multi line work and stuff.
	private JTextField textField = new JTextField();
	
	
//- Constructor --------------------------------------------------------------//
		
//- Getters ------------------------------------------------------------------//

	/**
	 * @return Text of the TextArea
	 */
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return this.textField.getText();
	}

	/**
	 * @return The text area component.
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		this.textField.setText((value == null ? "" : value.toString()));		

		return this.textField;
	}
	
	public void setTransferHandler(TransferHandler th){
		this.textField.setTransferHandler(th);
	}
}
