/**
 * 
 */
package gui.listener.mainview;

import gui.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author TDietl
 *
 */
public class ImportSDTMTerminologyAction implements ActionListener {
	private GUIController c;
	
	public ImportSDTMTerminologyAction(GUIController c) {
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.showImportSDTMTerminologyDialog();
	}

}
