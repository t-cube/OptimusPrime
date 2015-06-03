/**
 * Package for the Listeners used within the MainView.
 */
package gui.listener.mainview;

import gui.GUIController;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Select listener for the Database Combobox in the MainView.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class SelectDatabaseItem implements ItemListener {
	private GUIController c;
	
	public SelectDatabaseItem(GUIController c){
		this.c = c;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(
	 * java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange() == ItemEvent.SELECTED){
			c.loadTables(e.getItem().toString());
		}
	}

}
