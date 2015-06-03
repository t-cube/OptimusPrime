/**
 * Package containing the views of the GUI.
 */
package gui.views;

import gui.GUIController;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Base object for views used in the application. 
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-26
 */
public abstract class View extends JFrame {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
	
	protected HashMap<String, JComponent> controls;		

	
//- Constructor --------------------------------------------------------------//
	
	/**
	 * Constructor, instantiating the super class and setting up default 
	 * values for the class attributes.
	 */
	public View(){
		super();
		this.controls = new HashMap<String, JComponent>();
	}
	
	/**
	 * Constructor, instantiating the super class with the given title and 
	 * setting up default values for the class attributes.
	 */
	public View(String title){
		super(title);
		this.controls = new HashMap<String, JComponent>();
	}
	

//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//


//- Getters ------------------------------------------------------------------//
	
	/**
	 * @return The controls map, containing all control components of the view.
	 */
	public HashMap<String, JComponent> getControls(){
		return this.controls;
	}
	
	/**
	 * @param key Name of the control.
	 * @return The control component, selected by the given name.
	 */
	public JComponent getControl(String key){
		return this.controls.get(key);
	}


	//- Setters ------------------------------------------------------------------//
	
	/**
	 * Sets the event listener for the controls of this view.
	 * @param c GUIController object, probably used in the event listeners.
	 */
	public abstract void setEventListener(GUIController c);
}
