/**
 * Package for the Listeners used within the AddDatabaseDialog.
 */
package gui.listener.importsdtmterminologydialog;

import gui.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the save action for the AddDatabase dialog.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class SaveAction implements ActionListener {
	private GUIController c;
	
	public SaveAction(GUIController c){
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		c.importSDTMTerminology();
	}

}
