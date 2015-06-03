/**
 * Package for the Listeners used within the MainView.
 */
package gui.listener.mainview;

import gui.GUIController;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * WindowListener for the MainView frame.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class MainViewWindow implements WindowListener {
	private GUIController c;
	
	public MainViewWindow(GUIController c) {
		this.c = c;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(
	 * java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(
	 * java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		c.close();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(
	 * java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(
	 * java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(
	 * java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(
	 * java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(
	 * java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

}
