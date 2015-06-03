/**
 * Package for all dialogs used in the GUI. 
 */
package gui.dialogs;

import gui.GUIController;
import gui.listener.importtargetstructuredialog.SaveAction;

import javax.swing.JFrame;
import javax.swing.JButton;

/**
 * Dialog, which lets the user select a target structure, specify an alias and 
 * add the structure to the project.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class ImportTargetStructureDialog extends SimpleImportDialog {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
	
	
//- Constructor --------------------------------------------------------------//	

	/**
	 * Constructs the dialog by constructing the super class and binding it to 
	 * the given JFrame object.
	 * @param frame A JFrame object the dialog will be bound to.
	 */
	public ImportTargetStructureDialog(JFrame frame) {
		super(frame);
	}
	
	/**
	 * Constructs the dialog by constructing the super class and binding it to 
	 * the given JFrame object.
	 * @param frame A JFrame object the dialog will be bound to.
	 * @param title Title for the JDialog object.
	 */
	public ImportTargetStructureDialog(JFrame frame, String title) {
		super(frame, title);
	}


//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//


//- Getters ------------------------------------------------------------------//


//- Setters ------------------------------------------------------------------//

	@Override
	public void setEventListener(GUIController c){
		super.setEventListener(c);
		((JButton)this.controls.get("btn_save")).addActionListener(
															new SaveAction(c));
	}
}
