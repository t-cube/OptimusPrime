/**
 * Package containing helper classes for Swing components. In this case helper
 * classes for the JTable component.
 */
package gui.helper.table;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;
import javax.swing.table.TableCellEditor;

/**
 * Cell Editor for the JTable to allow editing the cell in a multi line way.
 * Thank you captain obvious.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class MultiLineCellEditor extends AbstractCellEditor 
								 implements TableCellEditor {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
	
	//The actual text area component doing the multi line work and stuff.
	private JTextArea textArea = new JTextArea();
	
	
//- Constructor --------------------------------------------------------------//
	
	/**
	 * Setting default properties for the TextArea Component. 
	 */
	public MultiLineCellEditor(){
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setOpaque(true);		
	}
	
	
//- Getters ------------------------------------------------------------------//

	/**
	 * @return Text of the TextArea
	 */
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return this.textArea.getText();
	}

	/**
	 * @return The text area component.
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		this.textArea.setText((value == null ? "" : value.toString()));		

		return this.textArea;
	}
	
	public void setTransferHandler(TransferHandler th){
		this.textArea.setTransferHandler(th);
	}
}
