/**
 * Package containing classes used to create the GUI of the application.
 */
package gui;

import transform.models.sdtmterminology.SDTMTerminologyModel;
import transform.models.sourcedb.SourceDatabasesModel;
import transform.models.targetstructures.TargetStructuresModel;

/**
 * General model, containing nearly all data shown in the MainView.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-01
 */
public class GUIModel {
	private SourceDatabasesModel sourceDatabasesModel;
	private SDTMTerminologyModel sdtmTerminologyModel;
	private TargetStructuresModel targetStructuresModel;
	
	
//- Constructor --------------------------------------------------------------//	
	
	public GUIModel(){
		this.sourceDatabasesModel = new SourceDatabasesModel();
		this.targetStructuresModel = new TargetStructuresModel();
	}
	
	
//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//


//- Getters ------------------------------------------------------------------//
	
	/**
	 * @return The model containing the source databases.
	 */
	public SourceDatabasesModel getSourceDatabasesModel() {
		return sourceDatabasesModel;
	}
	
	
	/**
	 * @return The model for the SDTM Terminology Tree.
	 */
	public SDTMTerminologyModel getSdtmTerminologyModel() {
		return sdtmTerminologyModel;
	}
			
	
	/**
	 * @return the targetStructuresModel
	 */
	public TargetStructuresModel getTargetStructuresModel() {
		return targetStructuresModel;
	}


//- Setters ------------------------------------------------------------------//

	/**
	 * @param targetStructuresModel the targetStructuresModel to set
	 */
	public void setTargetStructuresModel(
								TargetStructuresModel targetStructuresModel) {
		this.targetStructuresModel = targetStructuresModel;
	}

	/**
	 * @param sourceDatabasesModel A SourceDatabasesModel object, containing the
	 * 								the source databases.
	 */
	public void setSourceDatabasesModel(
								SourceDatabasesModel sourceDatabasesModel) {
		this.sourceDatabasesModel = sourceDatabasesModel;
	}
	
	
	/**
	 * @param sdtmTerminologyModel The model for the SDTM Terminology Tree.
	 */
	public void setSdtmTerminologyModel(
								SDTMTerminologyModel sdtmTerminologyModel) {
		this.sdtmTerminologyModel = sdtmTerminologyModel;
	}
	
	
}
