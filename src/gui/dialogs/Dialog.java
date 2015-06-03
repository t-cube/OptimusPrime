/**
 * Package for all dialogs used in the GUI. 
 */
package gui.dialogs;

import gui.GUIController;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The abstract base class for dialog objects.
 * @author Torsten Dietl
 * @version 0.1.0;
 * @since 2015-04-07
 */
public abstract class Dialog extends JDialog {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
	
	/**
	 *  The controls map.
	 */
	protected HashMap<String, JComponent> controls;
	
	
//- Constructor --------------------------------------------------------------//
	
	/**
	 * Constructs the super class and initiates this instance.
	 */
	public Dialog(){
		super();
		initInstance();
	}
	
	/**
	 * Constructs the super class, depending on the given frame, and initiates 
	 * this instance.
	 * @param frame The parent JFrame object.
	 */
	public Dialog(JFrame frame){
		super(frame);
		initInstance();
	}
	
	/**
	 * Constructs the super class, depending on the given frame, sets the 
	 * modality, and initiates this instance.
	 * @param frame The parent JFrame object.
	 * @param modal If true the dialog blocks all processes lower than itself.
	 */
	public Dialog(JFrame frame, boolean modal){
		super(frame, modal);
		initInstance();
	}
	
	/**
	 * Constructs the super class, and sets for the dialog.
	 * @param title Title of the dialog frame.
	 */
	public Dialog(String title){
		super();
		this.setTitle(title);
		initInstance();
	}
	

//- Private Methods ----------------------------------------------------------//
	
	/**
	 * Initiates the instance, sets the class attributes, etc.
	 */
	private void initInstance(){
		this.controls = new HashMap<String, JComponent>();
	}
	

//- Public Methods -----------------------------------------------------------//	
	
	/**
	 * Used to create event listeners for the controls of the dialog. 
	 * @param c GUIController object, which can be passed to the listeners.
	 */
	public abstract void setEventListener(GUIController c);
	
	
//- Getters ------------------------------------------------------------------//
	
	/**	 
	 * @return Returns the controls map.
	 */
	public HashMap<String, JComponent> getControls(){
		return this.controls;
	}
	
	/**
	 * @param key The key of the control that should be returned.
	 * @return The control object mapped by the given key.
	 */
	public JComponent getControl(String key){
		return this.controls.get(key);
	}
}
