/**
 * Package for the Listeners used within the AddDatabaseDialog.
 */
package gui.listener.simpleimportdialog;

import gui.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the cancel action for the AddDatabase dialog.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class CancelAction implements ActionListener {
	private GUIController c;
	
	public CancelAction(GUIController c){
		this.c = c;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		c.disposeLastDialog();
	}

}
