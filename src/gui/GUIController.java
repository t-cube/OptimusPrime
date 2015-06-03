/**
 * Package containing classes used to create the GUI of the application.
 */
package gui;

import filewrapper.msexcel.MSExcelWrapper;
import gui.dialogs.ImportSDTMTerminologyDialog;
import gui.dialogs.ImportSourceDatabaseDialog;
import gui.dialogs.Dialog;
import gui.dialogs.ImportTargetStructureDialog;
import gui.dialogs.SimpleImportDialog;
import gui.views.MainView;
import gui.views.View;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import transform.importmanager.SDTMDomainImportManager;
import transform.importmanager.SDTMTerminologyImportManager;
import transform.mapper.SDTMDomainRecordImportMapper;
import transform.mapper.SDTMTerminologyImportMapper;
import transform.models.sdtmterminology.SDTMTerminology;
import transform.models.sdtmterminology.SDTMTerminologyModel;
import transform.models.sourcedb.SourceTablesModel;
import transform.models.targetstructures.sdtm.SDTMDomain;
import transform.models.targetstructures.sdtm.SDTMDomainModel;
import transform.models.targetstructures.sdtm.SDTMDomainRecord;
import dbconnect.msaccess.FieldDefinition;
import dbconnect.msaccess.TableDefinition;


/**
 * The main controller for the application. Controls the GUI and underlying
 * controllers.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-26
 */
public class GUIController {
	private GUIModel gm;
	
	private MainView mv;
	private HashMap<String, View> views;
	private Stack<Dialog> dialogs; 

	
//- Constructor --------------------------------------------------------------//
	
	/**
	 * Simple constructor for the GUIController class, instantiates the
	 * attributes and opens the MainView.
	 */
	public GUIController(){
		this.gm = new GUIModel();
		
		this.views = new HashMap<String, View>();
		this.dialogs = new Stack<Dialog>();
		
		this.mv = new MainView();
		this.mv.setEventListener(this);
		this.views.put("MainView", this.mv);
	}
	
	/**
	 *  Close the gui controller and the application.
	 */
	public void close(){		
		System.exit(0);
	}
	

//- Private Methods ----------------------------------------------------------//


//- Public Methods -----------------------------------------------------------//
	
	/**
	 * Opens the import dialog for adding a target structure.
	 */
	public void showImportTargetStructureDialog(){
		Dialog d = new ImportTargetStructureDialog(this.mv, 
													"Import Target Structure");
		d.setEventListener(this);
		//d.setModal(true);
		this.dialogs.push(d);
	}
	
	/**
	 * Opens the import dialog for adding a source database.
	 */
	public void showImportSourceDatabaseDialog(){
		Dialog d = new ImportSourceDatabaseDialog(this.mv, 
													"Import Source Database");
		d.setEventListener(this);
		//d.setModal(true);
		this.dialogs.push(d);
	}
	
	/**	
	 * Opens the import dialog for adding the SDTM Terminology codelists.
	 */
	public void showImportSDTMTerminologyDialog(){
		Dialog d = new ImportSDTMTerminologyDialog(this.mv, 
										"Import SDTM Terminology Codelists");
		d.setEventListener(this);
		//d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.dialogs.push(d);										
	}
	
	/**
	 * Closes a view.
	 * @param view The name of the view, which should be closed.
	 */
	public void disposeView(String view){
		this.views.get(view).dispose();
		this.views.remove(view);
		
		if (view.equals("MainView")){
			this.mv = null;
		}
	}
	
	/**
	 * Closes the current dialog (i.e. the last created one).
	 */
	public void disposeLastDialog(){
		Dialog d = dialogs.pop();
		d.setModal(false);
		d.setModalityType(Dialog.ModalityType.MODELESS);
		d.dispose();
	}
	
	/**
	 * Adds a target structure to the models and the GUI.
	 */
	public void addTargetStructure(){
		Dialog d = this.dialogs.peek(); 
		
		if (d instanceof SimpleImportDialog){
			String alias = ((JTextField)d.getControl("txt_file_alias"))
																	.getText();
			String path = ((JTextField)d.getControl("txt_file_path")).getText();
			
			if (alias!=null && path!=null){
				if (alias!="" && path!=""){
					SDTMDomainRecordImportMapper mapper = 
											new SDTMDomainRecordImportMapper();
					mapper.addMapping(SDTMDomainRecord.KEYS.ASSUMPTIONS, 0);
					mapper.addMapping(SDTMDomainRecord.KEYS.VARIABLE_NAME, 0);
					mapper.addMapping(SDTMDomainRecord.KEYS.VARIABLE_LABEL, 1);
					mapper.addMapping(SDTMDomainRecord.KEYS.TYPE, 2);
					mapper.addMapping(SDTMDomainRecord.KEYS.CODE_LIST, 3);
					mapper.addMapping(SDTMDomainRecord.KEYS.ROLE, 4);
					mapper.addMapping(SDTMDomainRecord.KEYS.CDISC_NOTES, 5);
					mapper.addMapping(SDTMDomainRecord.KEYS.CORE, 6);
					
					SDTMDomainImportManager importManager = 
												new SDTMDomainImportManager();
					MSExcelWrapper fileWrapper = new MSExcelWrapper(path, 0);
					
					SDTMDomainModel sdtmModel = new SDTMDomainModel(mapper, 
																fileWrapper, 
																importManager);
					
					this.gm.getTargetStructuresModel().addSubModel(alias, 
																	sdtmModel);
					
					@SuppressWarnings("unchecked")
					JComboBox<String> cbx = (JComboBox<String>) 
														this.mv.getControl(
														"cbx_target_structure");
					
					cbx.addItem(alias);

					this.disposeLastDialog();
				}
			}
		}
	}
	
	/**
	 * Imports the SDTM terminology code lists into the model and the GUI.
	 */
	public void importSDTMTerminology(){
		Dialog d = this.dialogs.peek(); 
		
		if (d instanceof SimpleImportDialog){
			String alias = ((JTextField)d.getControl("txt_file_alias"))
																	.getText();
			String path = ((JTextField)d.getControl("txt_file_path")).getText();
			
			if (alias!=null && path!=null){
				if (alias!="" && path!=""){
					SDTMTerminologyImportMapper mapper = 
											new SDTMTerminologyImportMapper();
					
					mapper.addMapping(SDTMTerminology.KEYS.CODE, 0);
					mapper.addMapping(SDTMTerminology.KEYS.CODE_LIST_CODE, 1);
					mapper.addMapping(SDTMTerminology.KEYS.EXTENSIBLE, 2);
					mapper.addMapping(SDTMTerminology.KEYS.CODE_LIST_NAME, 3);
					mapper.addMapping(SDTMTerminology.KEYS.SUBMISSION_VALUE, 4);
					mapper.addMapping(SDTMTerminology.KEYS.CDISC_SYNONYMS, 5);
					mapper.addMapping(SDTMTerminology.KEYS.CDISC_DEFINITION, 6);
					mapper.addMapping(
									SDTMTerminology.KEYS.NCI_PREFERRED_TERM,7);
					
					SDTMTerminologyImportManager importManager = 
											new SDTMTerminologyImportManager();
					MSExcelWrapper fileWrapper = new MSExcelWrapper(path, 1);
					
					SDTMTerminologyModel sdtmModel = 
											new SDTMTerminologyModel(mapper, 
																fileWrapper,
																importManager);
					
					this.gm.setSdtmTerminologyModel(sdtmModel);
													
					JTree jt = (JTree)this.mv.getControl("tree_codelists");
					
					jt.setModel(new DefaultTreeModel(sdtmModel.getData()));
					
					this.disposeLastDialog();
				}
			}
		}
	}
	
	/**
	 * Adds a source database to the models and the GUI.
	 */
	public void addSourceDatabase(){
		Dialog d = this.dialogs.peek(); 
		
		if (d instanceof SimpleImportDialog){
			String alias = ((JTextField)d.getControl("txt_file_alias"))
																	.getText();
			String path = ((JTextField)d.getControl("txt_file_path")).getText();
			
			if (alias!=null && path!=null){
				if (alias!="" && path!=""){
					this.gm.getSourceDatabasesModel().addTablesModel(alias,
																	path);
					this.disposeLastDialog();
									
					@SuppressWarnings("unchecked")
					JComboBox<String> jcbx = (JComboBox<String>)this.mv
													.getControl("cbx_database");
					
					
					jcbx.addItem(alias);
				}
			}
		}
	}
		
	
	/**
	 * Loads all domains of the selected target structure in the GUI.
	 * @param alias Alias name of the selected target structure.
	 */
	public void loadDomains(String alias){
		SDTMDomainModel sdtmModel = (SDTMDomainModel) 
										this.gm.getTargetStructuresModel()
														.getData().get(alias);
		
		if (sdtmModel != null){		
			Set<String> domainNames;		
			domainNames = sdtmModel.getData().keySet();
					
			if (domainNames != null){
				@SuppressWarnings("unchecked")
				JComboBox<String> cbx = (JComboBox<String>) this.mv.getControl(
																 "cbx_domain");
				
				for (String domainName : domainNames)
					cbx.addItem(domainName);
			}	
		}
	}
	
	/**
	 * Loads all variables of the selected domain in the GUI.
	 * @param domainName The name of the selected domain.
	 */
	@SuppressWarnings("unchecked")
	public void loadVariables(String domainName){
		JComboBox<String> cbx = (JComboBox<String>)this.mv.getControl(
														"cbx_target_structure");
		String alias = cbx.getSelectedItem().toString();
		
		SDTMDomainModel sdtmModel;
		sdtmModel = (SDTMDomainModel) this.gm.getTargetStructuresModel()
														.getData().get(alias);
		
		if (sdtmModel != null){		
			SDTMDomain domain;		
			domain = sdtmModel.getData().get(domainName);
			if (domain != null){
				JList<String> variablesList = (JList<String>) 
													this.mv.getControl(
															"lst_variables");
				
				DefaultListModel<String> listModel = (DefaultListModel<String>) 
						variablesList.getModel();
				
				listModel.clear();
				for (SDTMDomainRecord record : domain
												.getSpecification().values()){
					listModel.addElement(record.getVariableName());
				}
				
				((JLabel)this.mv.getControl("lbl_mapping")).setText(
													"Mapping Domain: " 
													+ domain.getDomainName());
			}
		}		
	}
	
	/**
	 * Loads all tables of the selected source database in the GUI.
	 * @param dbAlias Alias name of the selected source database connection.
	 */
	public void loadTables(String dbAlias){
		SourceTablesModel tablesModel;
		tablesModel = this.gm.getSourceDatabasesModel().getData().get(dbAlias); 
		if (tablesModel != null){		
			Set<String> tblNames;		
			tblNames = tablesModel.getData().keySet();
					
			if (tblNames != null){
				@SuppressWarnings("unchecked")
				JComboBox<String> cbx = (JComboBox<String>) this.mv.getControl(
																 "cbx_tables");
				
				for (String tblName : tblNames)
					cbx.addItem(tblName);
			}	
		}
	}
	

	/**
	 * Loads all fields of the selected table in the GUI.
	 * @param tblName The name of the selected table.
	 */
	@SuppressWarnings("unchecked")
	public void loadFields(String tblName){
		JComboBox<String> cbx = (JComboBox<String>)this.mv.getControl(
														"cbx_database");
		String dbAlias = cbx.getSelectedItem().toString();
		
		SourceTablesModel tablesModel;
		tablesModel = this.gm.getSourceDatabasesModel().getData().get(dbAlias);
		
		if (tablesModel != null){		
			TableDefinition tbl;		
			tbl = tablesModel.getData().get(tblName);
			if (tbl != null){
				JList<String> fieldList = (JList<String>) this.mv.getControl(
						"lst_fields");
				
				DefaultListModel<String> listModel = (DefaultListModel<String>) 
								fieldList.getModel();
				
				listModel.clear();
				for (FieldDefinition fd : tbl.getFields().values()){
					listModel.addElement(fd.getFieldName());
				}		
			}
		}		
	}
	

	public void addVariable(String item) {
		JScrollPane scroll = (JScrollPane) this.mv.getControl("tbl_mapping");
		JTable jt = (JTable) scroll.getViewport().getView();	
		DefaultTableModel dtm = (DefaultTableModel) jt.getModel();
		Object[] row = new Object[dtm.getColumnCount()];
		row[0] = item;
		dtm.addRow(row);
	}


//- Getters ------------------------------------------------------------------//
	
	/**
	 * @return The current open dialog (the last one created).
	 */
	public Dialog getCurrentDialog(){
		return dialogs.peek();
	}

	/**
	 * @param key Name of the view, which should be returned.
	 * @return The selected view.
	 */
	public View getView(String key){
		return views.get(key);
	}	

//- Setters ------------------------------------------------------------------//
	
}
