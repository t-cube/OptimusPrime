/**
 * Package for all dialogs used in the GUI. 
 */
package gui.dialogs;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.GUIController;
import gui.listener.simpleimportdialog.CancelAction;
import gui.listener.simpleimportdialog.OpenAction;

/**
 * Simple dialog asking for a file path and an alias for the file. 
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SimpleImportDialog extends Dialog {
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
	public SimpleImportDialog(JFrame frame){
		super(frame);
		
		this.setTitle("Import File");
		
		initDialog();
		
		this.setResizable(false);
		
		this.pack();
		
		this.setVisible(true);
	}
	
	/**
	 * Constructs the dialog by constructing the super class and binding it to 
	 * the given JFrame object.
	 * @param frame A JFrame object the dialog will be bound to.
	 * @param title Title for the JDialog object.
	 */
	public SimpleImportDialog(JFrame frame, String title){
		super(frame);
		
		this.setTitle(title);
		
		initDialog();
				
		this.setResizable(false);
		
		this.pack();
		
		this.setVisible(true);
	}


//- Private Methods ----------------------------------------------------------//
	
	/**
	 * Initiates the JDialog object.
	 */
	private void initDialog(){
		this.controls = new HashMap<String, JComponent>();
		
		createComponents();
		layoutComponents();
	}

	/**
	 * Creates the components of this dialog.
	 */
	private void createComponents(){
		JComponent jc;
		
		jc = new JLabel("File Alias");
		this.controls.put("lbl_file_alias", jc);
		
		jc = new JTextField("");
		((JTextField) jc).setColumns(30);
		this.controls.put("txt_file_alias", jc);
		
		jc = new JLabel("File Path");
		this.controls.put("lbl_file_path", jc);
		
		jc = new JTextField("");
		((JTextField) jc).setColumns(30);
		jc.setEnabled(false);
		this.controls.put("txt_file_path", jc);
		
		jc = new JButton("Open File");
		this.controls.put("btn_open_file", jc);
		
		jc = new JButton("Create File Manager");
		this.controls.put("btn", jc);

		jc = new JButton("Save");
		this.controls.put("btn_save", jc);

		jc = new JButton("Cancel");
		this.controls.put("btn_cancel", jc);
	}
		
	/**
	 * Creates the layout for the components of this dialog.
	 */
	private void layoutComponents(){
		Container pane = this.getContentPane();
		pane.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(3, 3, 3, 3);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(this.controls.get("lbl_file_alias"), c);

		c.gridy = 1;
		pane.add(this.controls.get("lbl_file_path"), c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		pane.add(this.controls.get("txt_file_alias"), c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.gridwidth = 1;
		pane.add(this.controls.get("txt_file_path"), c);

		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0;
		c.gridwidth = 1;
		pane.add(this.controls.get("btn_open_file"), c);
		
		JPanel buttonPanel = new JPanel();
		this.controls.put("pnl_buttons", buttonPanel);
		buttonPanel.setLayout(new GridBagLayout());
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		buttonPanel.add(this.controls.get("btn_save"), c);
		
		c.gridx = 1;
		buttonPanel.add(this.controls.get("btn_cancel"), c);
		
		// Dummy label, to fix buttons at the bottom
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 1;
		pane.add(new JLabel(), c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 0;
		pane.add(buttonPanel, c);
	}
	
	
//- Public Methods -----------------------------------------------------------//


//- Getters ------------------------------------------------------------------//


//- Setters ------------------------------------------------------------------//

	@Override
	public void setEventListener(GUIController c) {
		((JButton) this.controls.get("btn_cancel")).addActionListener(
													new CancelAction(c));
		((JButton) this.controls.get("btn_open_file")).addActionListener(
														new OpenAction(c));
	}

}
