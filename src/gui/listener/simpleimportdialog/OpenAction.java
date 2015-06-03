/**
 * Package for the Listeners used within the AddDatabaseDialog.
 */
package gui.listener.simpleimportdialog;

import gui.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 * Implements the open action for the AddDatabase dialog.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class OpenAction implements ActionListener {
	private GUIController c;
	
	public OpenAction(GUIController c){
		this.c = c;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Creates a new file chooser to select the database file.
		JFileChooser chooser = new JFileChooser("Select File to add");
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setVisible(true);
		
		// shows the file chooser
		int result = chooser.showOpenDialog(c.getCurrentDialog());
		
		// if it gets a result
		if (result == JFileChooser.APPROVE_OPTION){
			// select the file and add it to the txt_db_path control
			File dbFile = chooser.getSelectedFile();
			JTextField txt = (JTextField) c.getCurrentDialog()
												.getControl("txt_file_path");
			txt.setText(dbFile.getPath());
		}
		
		// hide the file chooser
		chooser.setVisible(false);
	}

}
