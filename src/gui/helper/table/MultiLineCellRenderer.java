/**
 * Package containing helper classes for Swing components. In this case helper
 * classes for the JTable component.
 */
package gui.helper.table;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

/**
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class MultiLineCellRenderer extends JTextArea implements
		TableCellRenderer {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
    private List<Integer> rowColHeight = new ArrayList<Integer>();
	
	
//- Constructor --------------------------------------------------------------//

    /**
	 * Setting default properties for the TextArea Component. 
	 */
	public MultiLineCellRenderer(){
		setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
	}
	

//- Private Methods ----------------------------------------------------------//
	
	/**
	* Calculate the new preferred height for a given row, 
	* and sets the height on the table.
	*/
	private void adjustRowHeight(JTable table, int row, int column) {
		// necessary to get the correct preferred size of the text area,
		// because without a fixed with the text area does not wrap the text,
		// because it thinks it has unlimited width space.
		int cWidth = table.getColumnModel().getColumn(column).getWidth();
		this.setSize(new Dimension(cWidth, 1000));
		int prefH = getPreferredSize().height;
		
		// if there are rows, which heights were not stored, store the
		// current preferred height.
		while (rowColHeight.size() <= row) {
			rowColHeight.add(prefH);
		}
		
		// only if the table height is lower than the preferred adjust it.
		// this allows table rows to store bigger than necessary, which is 
		// appreciated.
		if (table.getRowHeight(row) < prefH) {
			table.setRowHeight(row, prefH);
		}
	}
	
	
//- Public Methods -----------------------------------------------------------//

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		 // TODO Auto-generated method stub
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		
		setFont(table.getFont());
		
		if (hasFocus) {
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			if (table.isCellEditable(row, column)) {
				setForeground(UIManager.getColor("Table.focusCellForeground"));
				setBackground(UIManager.getColor("Table.focusCellBackground"));
			}
		} else {
			setBorder(new EmptyBorder(1, 2, 1, 2));
		}
		
		setText((value == null) ? "" : value.toString());
		
		adjustRowHeight(table, row, column);
		return this;
	}
}
