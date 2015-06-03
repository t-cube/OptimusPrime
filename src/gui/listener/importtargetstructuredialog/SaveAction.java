/**
 * 
 */
package gui.listener.importtargetstructuredialog;

import gui.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author TDietl
 *
 */
public class SaveAction implements ActionListener {
	GUIController c;
	
	public SaveAction(GUIController c){
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.addTargetStructure();
	}

}
