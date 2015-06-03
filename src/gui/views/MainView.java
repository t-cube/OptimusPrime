/**
 * Package containing the views of the GUI.
 */
package gui.views;

import gui.GUIController;
import gui.helper.ListTransferHandler;
import gui.helper.table.CellTransferHandler;
import gui.helper.table.MultiLineCellEditor;
import gui.helper.table.MultiLineCellRenderer;
import gui.helper.table.SingleLineCellEditor;
import gui.helper.table.TableRowResizer;
import gui.helper.table.TableTransferHandler;
import gui.listener.mainview.AddDatabaseAction;
import gui.listener.mainview.AddTargetStructureAction;
import gui.listener.mainview.AddVariableMouse;
import gui.listener.mainview.CloseAction;
import gui.listener.mainview.ImportSDTMTerminologyAction;
import gui.listener.mainview.MainViewWindow;
import gui.listener.mainview.SelectDatabaseItem;
import gui.listener.mainview.SelectDomainItem;
import gui.listener.mainview.SelectTableItem;
import gui.listener.mainview.SelectTargetStructureItem;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

/**
 * The main frame of the application.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-26
 */
public class MainView extends View {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;
	private HashMap<String, JMenuItem> menuItems;
	
	
//- Constructor --------------------------------------------------------------//
	
	/**
	 * Constructs the main frame of the application and displays it.
	 */
	public MainView(){
		super("Optimus Prime v0.1.0");

		// initialize attributes
		this.menuItems = new HashMap<String, JMenuItem>();
		
		// set the size! important to set it before the layout components
		// because they relay on it
		this.setSize(800, 600);
		createMenu();
		createComponents();
		layoutComponents();
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
		this.setVisible(true);
	}
	

//- Private Methods ----------------------------------------------------------//
	
	/**
	 * Creates the menu objects.
	 */
	private void createMenu(){
		this.setJMenuBar(new JMenuBar());
		
		JMenu m = new JMenu("File");
		this.getJMenuBar().add(m);
		
		JMenuItem mi = new JMenuItem("New Project");
		m.add(mi);
		
		mi = new JMenuItem("Close");
		m.add(mi);
		
		m = new JMenu("Project");
		this.getJMenuBar().add(m);
		
		mi = new JMenuItem("Add Target Structure");
		this.menuItems.put("m_add_target_structure", mi);
		m.add(mi);
		
		mi = new JMenuItem("Import Code Lists");
		this.menuItems.put("m_import_sdtm_terminology", mi);
		m.add(mi);
		
		mi = new JMenuItem("Add Database");
		this.menuItems.put("m_add_database", mi);
		m.add(mi);
	}
	
	/**
	 * Creates the control components.
	 */
	private void createComponents(){
		JComponent jc;	
		
		// SDTM Version
		jc = new JLabel("Target Structure");
		this.controls.put("lbl_target_structure", jc);		
		
		jc = new JComboBox<String>();
		this.controls.put("cbx_target_structure", jc);		
		
		// Domain
		jc = new JLabel("Domain");
		this.controls.put("lbl_domain", jc);		
		
		jc = new JComboBox<String>();
		this.controls.put("cbx_domain", jc);

		// Variables
		jc = new JLabel("Variables");
		this.controls.put("lbl_variables", jc);

		JList<String> jList = new JList<String>(new DefaultListModel<String>());
		jList.setDropMode(DropMode.ON_OR_INSERT);
		jList.setTransferHandler(new ListTransferHandler());
		jList.setDragEnabled(true);
		jList.setName("Variables"); // Important for the Drag&Drop
		this.controls.put("lst_variables", jList);
		
		// Codelists
		jc = new JLabel("Codelists");
		this.controls.put("lbl_codelists", jc);

		jc = new JTree(new DefaultTreeModel(null));		
		this.controls.put("tree_codelists", jc);
		
		
		// Mapping Table
		jc = new JLabel ("Mapping Domain: ");
		this.controls.put("lbl_mapping", jc);
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Variable");
		dtm.addColumn("Codelist");
		dtm.addColumn("Formula");
		dtm.addColumn("Source");
		
		JTable jt = new JTable(dtm);
		
		jt.getColumnModel().getColumn(2).setCellRenderer(new MultiLineCellRenderer());
		MultiLineCellEditor mlce = new MultiLineCellEditor();
		mlce.setTransferHandler(new CellTransferHandler());
		jt.getColumnModel().getColumn(2).setCellEditor(mlce);	
		
		SingleLineCellEditor slce = new SingleLineCellEditor();
		slce.setTransferHandler(new CellTransferHandler());
		jt.getColumnModel().getColumn(0).setCellEditor(slce);
		
		jt.setTransferHandler(new TableTransferHandler());
		new TableRowResizer(jt);
		JScrollPane scrollTable = new JScrollPane(jt);
		this.controls.put("tbl_mapping", scrollTable);
		
		
		// Database
		jc = new JLabel("Database");
		this.controls.put("lbl_database", jc);
		
		jc = new JComboBox<String>();
		this.controls.put("cbx_database", jc);
				
		// Tables
		jc = new JLabel("Tables");
		this.controls.put("lbl_tables", jc);
		
		jc = new JComboBox<String>();
		this.controls.put("cbx_tables", jc);
				
		// Fields
		jc = new JLabel("Fields");
		this.controls.put("lbl_fields", jc);

		jList = new JList<String>(new DefaultListModel<String>());
		jList.setDropMode(DropMode.ON_OR_INSERT);
		jList.setTransferHandler(new ListTransferHandler());
		jList.setDragEnabled(true);
		jList.setName("Fields"); // Important for the Drag&Drop
		this.controls.put("lst_fields", jList);
		
		// Buttons		
		jc = new JButton("Generate Mapping Documentation");
		this.controls.put("btn_mapping", jc);
		
		jc = new JButton("Generate define XML");
		this.controls.put("btn_xml", jc);
		
		jc = new JButton("Generate Target Structure DB");
		this.controls.put("btn_sdtm", jc);
		
		jc = new JButton("Save Project");
		this.controls.put("btn_save", jc);
		
		jc = new JButton("Load Project");
		this.controls.put("btn_load", jc);
		
		jc = new JButton("Close");
		this.controls.put("btn_close", jc);
		
	}
	
	/**
	 * Creates the component layout.
	 */
	private void layoutComponents(){
		// create standards for GridBagconstraints
		GridBagConstraints c = new GridBagConstraints();

		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(3,3,3,3);		
		
		// create first column panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		this.controls.put("pnl_split_first_column", panel);
				
		c.gridy = 0;
		panel.add(this.controls.get("lbl_target_structure"), c);
		c.gridy = 1;
		panel.add(this.controls.get("cbx_target_structure"), c);
		c.gridy = 2;
		panel.add(this.controls.get("lbl_domain"), c);
		c.gridy = 3;
		panel.add(this.controls.get("cbx_domain"), c);
		
		// left lower boxes: Variables and Codelists
		JPanel panelUpper = new JPanel();
		JPanel panelLower = new JPanel();
		panelUpper.setLayout(new GridBagLayout());
		panelLower.setLayout(new GridBagLayout());
		
		JSplitPane splitFirstColumn = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
											panelUpper, panelLower);
		splitFirstColumn.setOneTouchExpandable(true);
		splitFirstColumn.setResizeWeight(0.5);
		
		c.gridy = 4;
		c.weighty = 1.0;
		panel.add(splitFirstColumn, c);

		// Variables, scrollable
		c.weighty = 0.0;
		c.gridy = 0;
		panelUpper.add(this.controls.get("lbl_variables"), c);
		c.gridy = 1;
		c.weighty = 1.0;
		JScrollPane scrollVariables = new JScrollPane(
											this.controls.get("lst_variables"));
		panelUpper.add(scrollVariables, c);
		
		// Codelists, scrollable
		c.gridy = 0;
		c.weighty = 0.0;
		panelLower.add(this.controls.get("lbl_codelists"), c);
		c.gridy = 1;
		c.weighty = 1.0;
		JScrollPane scrollCodelists = new JScrollPane(
										this.controls.get("tree_codelists"));
		panelLower.add(scrollCodelists, c);
	
		
		// create second column panel
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		this.controls.put("pnl_split_second_column", panel);
		
		// Mapping table
		c.gridy = 0;
		c.weighty = 0;
		panel.add(this.controls.get("lbl_mapping"), c);
		c.gridy = 1;
		c.weighty = 1;
		panel.add(this.controls.get("tbl_mapping"), c);
		

		// create third column panel
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		this.controls.put("pnl_split_third_column", panel);
		
		// Database
		c.weighty = 0;
		c.gridy = 0;
		panel.add(this.controls.get("lbl_database"), c);
		
		c.gridy = 1;
		panel.add(this.controls.get("cbx_database"), c);
		
		// Tables
		c.gridy = 2;
		panel.add(this.controls.get("lbl_tables"), c);
		
		c.gridy = 3;
		panel.add(this.controls.get("cbx_tables"), c);
		
		// Fields, scrollable
		c.gridy = 4;
		panel.add(this.controls.get("lbl_fields"), c);
		
		JScrollPane scrollFields = new JScrollPane(
											this.controls.get("lst_fields"));
		c.weighty = 1;
		c.gridy = 5;
		panel.add(scrollFields, c);
		
		// Buttons
		JComponent buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		
		this.controls.put("pnl_buttons", panel);
		c.weighty = 0;
		c.gridy = 6;
		panel.add(buttonPanel, c);

		c.gridy = 0;
		buttonPanel.add(this.controls.get("btn_mapping"), c);
		c.gridy = 1;
		buttonPanel.add(this.controls.get("btn_xml"), c);
		c.gridy = 2;
		buttonPanel.add(this.controls.get("btn_sdtm"), c);
		c.gridy = 3;
		buttonPanel.add(this.controls.get("btn_save"), c);
		c.gridy = 4;
		buttonPanel.add(this.controls.get("btn_load"), c);
		c.gridy = 5;
		buttonPanel.add(this.controls.get("btn_close"), c);
		
		
		
		// set up split panes and add the column panels 
		JSplitPane split12 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
								this.controls.get("pnl_split_first_column"),
								this.controls.get("pnl_split_second_column"));
		
		split12.setOneTouchExpandable(true);
		split12.setDividerLocation(200 + split12.getInsets().left);
		split12.setResizeWeight(0.0);
		
		
		JSplitPane split3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, split12,
								this.controls.get("pnl_split_third_column"));
		
		split3.setOneTouchExpandable(true);
		split3.setDividerLocation(this.getSize().width 
									- split3.getInsets().right 
									- split3.getDividerSize() - 200);
		split3.setResizeWeight(1.0);
		
		
		// add the split panes to the content pane
		Container pane = this.getContentPane();
		GridLayout layout = new GridLayout(1,3);				
		pane.setLayout(layout);					
		
		pane.add(split3);
	}
	
	
//- Public Methods -----------------------------------------------------------//


//- Getters ------------------------------------------------------------------//


//- Setters ------------------------------------------------------------------//
	
	@SuppressWarnings("unchecked")
	@Override
	public void setEventListener(GUIController c) {
		// TODO Auto-generated method stub
	
		// IMPORTANT: Set the Window Listener to close the application if the 
		// 			  main view is closed
		this.addWindowListener(new MainViewWindow(c));
		
		// Add ActionListener for MenuItems
		this.menuItems.get("m_add_target_structure").addActionListener(
											new AddTargetStructureAction(c));
		this.menuItems.get("m_add_database").addActionListener(
													new AddDatabaseAction(c));
		this.menuItems.get("m_import_sdtm_terminology").addActionListener(
											new ImportSDTMTerminologyAction(c));
		
		// Add ActionListener for Buttons
		((JButton) this.controls.get("btn_close")).addActionListener(
															new CloseAction(c));
		
		// Add ItemListener for Comboboxes
		JComboBox<String> jComboBox = (JComboBox<String>) this.controls
												.get("cbx_target_structure");
		jComboBox.addItemListener(new SelectTargetStructureItem(c));
		
		
		jComboBox = (JComboBox<String>) this.controls.get("cbx_domain");
		jComboBox.addItemListener(new SelectDomainItem(c));

		jComboBox = (JComboBox<String>) this.controls.get("cbx_database");
		jComboBox.addItemListener(new SelectDatabaseItem(c));
		
		
		jComboBox = (JComboBox<String>) this.controls.get("cbx_tables");
		jComboBox.addItemListener(new SelectTableItem(c));
		
		// Add MouseListener to Lists
		JList<String> jList = (JList<String>) 
											this.controls.get("lst_variables");
		jList.addMouseListener(new AddVariableMouse(c));
	}

}
