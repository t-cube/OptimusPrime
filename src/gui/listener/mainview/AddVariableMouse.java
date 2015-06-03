/**
 * 
 */
package gui.listener.mainview;

import gui.GUIController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

/**
 * @author TDietl
 *
 */
public class AddVariableMouse implements MouseListener {
	GUIController c;
	
	public AddVariableMouse(GUIController c){
		this.c = c;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			if (e.getComponent() instanceof JList){
				@SuppressWarnings("unchecked")
				JList<String> jl = (JList<String>) e.getComponent();
				
				if (jl.getName().equals("Variables")){
					String item = jl.getSelectedValue();
					c.addVariable(item);
				}				
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
