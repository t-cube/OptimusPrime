/**
 * Package for the Listeners used within the MainView.
 */
package gui.listener.mainview;

import gui.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action for the MainView to open the AddDatabaseDialog.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class AddDatabaseAction implements ActionListener {
	private GUIController c;
	
	public AddDatabaseAction(GUIController c) {
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		c.showImportSourceDatabaseDialog();
	}

}
