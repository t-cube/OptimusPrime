/**
 * Package containing helper classes for Swing components. In this case helper
 * classes for the JTable component.
 */
package gui.helper.table;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;

/**
 * This class allows to resize individual table rows. It's based on the website: 
 * http://www.jroller.com/santhosh/entry/make_jtable_resiable_better_than
 * (last checked 2015-04-07) 
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class TableRowResizer extends MouseInputAdapter {
    public static Cursor resizeCursor = Cursor.getPredefinedCursor(
    												Cursor.N_RESIZE_CURSOR); 
 
    private int mouseYOffset, resizingRow; 
    private Cursor otherCursor = resizeCursor; 
    private JTable table; 
 
    /**
     * Constructor, adding this class as mouse- and mousemotion listener to the
     * given table.
     * @param table The given table we want the table row resizer to apply.
     */
    public TableRowResizer(JTable table){ 
        this.table = table; 
        table.addMouseListener(this); 
        table.addMouseMotionListener(this); 
    } 
    
    
    /**
     * Checks if the point is on the edge of a row or within the row.
     * For more information see getResizingRow(Point, int).
     * @param p The mouse pointer coordinations.
     * @return If the mouse pointer is on the row edge it returns the regarding
     * 			row, otherwise it returns -1. 
     */
    private int getResizingRow(Point p){ 
    	// how "big" is the zone for the row edge
    	int bufferZoneSize = 5;    	
    	
    	// get the row we hover over
    	int row = table.rowAtPoint(p);
    	// only proceed if it is a valid row
        if(row == -1){ 
            return -1; 
        } 
        
        // get the column we hover over 
        int col = table.columnAtPoint(p); 
        // only proceed if valid
        if(col==-1) 
            return -1;
        
        // get the cell rectangle
        Rectangle r = table.getCellRect(row, col, true);
        // decrement it by the buffer zone size
        r.grow(0, -bufferZoneSize);
        
        // check if the mouse pointer hovers over the cell or over the 
        // bufferZone(row edge)
        if(r.contains(p)) 
            return -1; 
 
        // calculate the y-middlepoint of the cell
        int midPoint = r.y + r.height / 2; 
        // if the mouse point is smaller as the middle point we hover over the
        // top edge therefore we have to return the previous row, otherwise
        // we return the current row.
        int rowIndex = (p.y < midPoint) ? row - 1 : row; 
 
        return rowIndex;  
    } 
 
    public void mousePressed(MouseEvent e){ 
        Point p = e.getPoint(); 
 
        resizingRow = getResizingRow(p); 
        mouseYOffset = p.y - table.getRowHeight(resizingRow); 
    } 
 
    /**
     * Swaps the cursor to the resizing cursor
     */
    private void swapCursor(){ 
        Cursor tmp = table.getCursor(); 
        table.setCursor(otherCursor); 
        otherCursor = tmp; 
    } 
 
    public void mouseMoved(MouseEvent e){ 
    	// if the pointer is in the buffer row and the current cursor 
    	// is not the resizeCursor or vice versa --> swap
        if((getResizingRow(e.getPoint())>=0) 
        		!= (table.getCursor() == resizeCursor)){ 
            swapCursor(); 
        } 
    } 
 
    public void mouseDragged(MouseEvent e){ 
        int mouseY = e.getY(); 
 
        if(resizingRow >= 0){ 
            int newHeight = mouseY - mouseYOffset; 
            if(newHeight > 0)
                table.setRowHeight(resizingRow, newHeight); 
        } 
    }
}
