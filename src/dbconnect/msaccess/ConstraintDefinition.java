/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

import util.DebugSystem;

/**
 * Wrapper class for defining a constraint. 
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-23
 */
public class ConstraintDefinition {
	private String constraintName = null;
	private CONSTRAINT constraint = null;
	private String[] fieldNames = null;
	private String refTableName = null;
	private String[] refTableFieldList = null;
	private CONSTRAINT[] refProperties = null;

//- Constructor --------------------------------------------------------------//
	
	/**
	 * Constructor to create a "normal" constraint (no foreign key constraint)
	 * @param constraintName The name for this constraint, has to be unique in 
	 * 						 the table the constraint is used in.
	 * @param constraint The "type" of constraint.
	 * @param fieldNames The fields included in the constraint.
	 */
	public ConstraintDefinition(String constraintName, CONSTRAINT constraint, 
								String[] fieldNames){
		if (!constraint.equals(CONSTRAINT.FOREIGN_KEY)){			
			setConstraintName(constraintName);
			setConstraint(constraint);
			setFieldNames(fieldNames);		
		}else{
			DebugSystem.logError("The foreign key constraint needs "
								+ "another constructor.");
		}
	}
	
	
	/**
	 * Constructor to create a foreign key constraint.
	 * @param constraintName The name for this constraint, has to be unique in 
	 * 						 the table the constraint is used in.
	 * @param constraint The "type" of constraint.
	 * @param fieldNames The fields included in the constraint.
	 * @param refTableName Name of the reference table.
	 * @param refTableFieldList Name of the fields which are referenced.
	 * @param refProperties Reference properties.
	 */
	public ConstraintDefinition(String constraintName, CONSTRAINT constraint,
								String[] fieldNames, String refTableName, 
								String[] refTableFieldList, 
								CONSTRAINT[] refProperties){
		

		setFieldNames(fieldNames);
		setConstraintName(constraintName);
		setConstraint(constraint);
		setRefTableName(refTableName);
		setRefTableFieldList(refTableFieldList);
		setRefProperties(refProperties);
		
		if (!constraint.equals(CONSTRAINT.FOREIGN_KEY)){
			DebugSystem.logWarning("The arguments refTableName, "
								 + "refTableFieldList and refProperties are not"
								 + " necessary for this kind of Constraint (" 
								 + constraint.toString() + ").");
		}
	}
	

	
//- Private Methods ----------------------------------------------------------//
		
		
//- Public Methods -----------------------------------------------------------//
	
	/**
	 * @return The string representation of the constraint definition.
	 */
	public String toString(){
		String result = "";
		
		if (this.constraintName!=null && this.constraint!=null && 
			this.fieldNames!=null){

			result = this.constraintName + this.constraint.toString();
			
			for (int i=0; i<this.fieldNames.length; i++)
				result += (i>0?", ": " (") + this.fieldNames[i];
			
			result += ")";
				
			if (this.constraint.equals(CONSTRAINT.FOREIGN_KEY)){
				if (this.refTableName!=null && this.refTableFieldList!=null 
						&& this.refProperties!=null){
					result += " REFERENCES " + this.refTableName;
					
					for (int i=0; i<this.refTableFieldList.length; i++)
						result += (i>0?", ":" (") + this.refTableFieldList[i];
					
					for (int i=0; i<this.refProperties.length; i++)
						result += (i>0?" ":") ") + this.refProperties[i]
															.toString();
					
					result += ")";
				}else{
					DebugSystem.logError("References for FOREIGN KEY Constraint"
									+ " not set - Cannot return constraint "
									+ "definition string.");
					result = "";
				}
			}
				
		}else{
			DebugSystem.logError("Constraint attributes not set - Cannot "
								+ "return constraint definition string.");
			result = "";
		}				
		
		return result;
	}
	
	
	
//- Getters ------------------------------------------------------------------//
	
	/**
	 * @return The name of the constraint.
	 */
	public String getConstraintName() {
		return constraintName;
	}

	
	/**
	 * @return The "type" of the constraint
	 */
	public CONSTRAINT getConstraint() {
		return constraint;
	}

	
	/**
	 * @return The names of the fields included in the constraint.
	 */
	public String[] getFieldNames() {
		return fieldNames;
	}

	
	/**
	 * @return The referenced table of the constraint.
	 */
	public String getRefTableName() {
		return refTableName;
	}

	
	/**
	 * @return The names of the fields referenced in the constraint.
	 */
	public String[] getRefTableFieldList() {
		return refTableFieldList;
	}

	
	/**
	 * @return The reference properties.
	 */
	public CONSTRAINT[] getRefProperties() {
		return refProperties;
	}
	
	
	
//- Setters ------------------------------------------------------------------//	
	
	/**
	 * @param constraintName The name for the constraint. Has to be unique over
	 * 						 the constrained table.
	 */
	public void setConstraintName(String constraintName) {
		if (Validator.validateObjectName(constraintName)){
			this.constraintName = constraintName;
		}else{
			DebugSystem.logError("The constraint name '" + constraintName + 
					"' is prohibited.");
			this.constraintName = null;
		}
	}

	
	/**
	 * @param constraint The constraint "type".
	 */
	public void setConstraint(CONSTRAINT constraint) {
		if (!(constraint.equals(CONSTRAINT.ON_DELETE) ||
				constraint.equals(CONSTRAINT.ON_UPDATE) ||
				constraint.equals(CONSTRAINT.SET_NULL))){
			this.constraint = constraint;
		}else{
			DebugSystem.logError(constraint.toString() + " is a reference "
							+ "property and cannot be used as a constraint.");
			this.constraint = null;
		}		
	}

	
	/**
	 * @param fieldNames The names of the fields included in the constraint.
	 */
	public void setFieldNames(String[] fieldNames) {
		String prohibitedNames = "";
		
		for (int i = 0; i<fieldNames.length; i++){
			if (!Validator.validateObjectName(fieldNames[i])){
				prohibitedNames += (prohibitedNames.equals("")?" ":", ")
									+ fieldNames[i]; 
			}
		}

		if (prohibitedNames.equals("")){
			this.fieldNames = fieldNames;
		}else{
			DebugSystem.logError("The following field name(s) is/are "
								+ "prohibited:" + prohibitedNames);
			this.fieldNames = null;
		}				
	}

	
	/**
	 * @param refTableName The name of the referenced table.
	 */
	public void setRefTableName(String refTableName) {
		if (Validator.validateObjectName(refTableName)){
			this.refTableName = refTableName;
		}else{
			DebugSystem.logError("The reference table name '" + refTableName + 
								 "' is prohibited.");
			this.refTableName = null;
		}
	}

	
	/**	
	 * @param refTableFieldList The names of the fields referenced in the 
	 * 							constraint.
	 */
	public void setRefTableFieldList(String[] refTableFieldList) {
		String prohibitedNames = "";
		
		for (int i = 0; i<refTableFieldList.length; i++){
			if (!Validator.validateObjectName(refTableFieldList[i])){
				prohibitedNames += (prohibitedNames.equals("")?" ":", ")
									+ refTableFieldList[i]; 
			}
		}
		
		if (prohibitedNames.equals("")){
			this.refTableFieldList = refTableFieldList;
		}else{
			DebugSystem.logError("The following reference field name(s) is/are "
								+ "prohibited:" + prohibitedNames);
			this.refTableFieldList = null;
		}	
	}

	
	/**
	 * @param refProperties The reference properties.
	 */
	public void setRefProperties(CONSTRAINT[] refProperties) {
		String propertyNames = "";
		
		for (int i=0; i<refProperties.length; i++){
			if (!(refProperties[i].equals(CONSTRAINT.ON_DELETE) ||
				  refProperties[i].equals(CONSTRAINT.ON_UPDATE) ||
				  refProperties[i].equals(CONSTRAINT.SET_NULL))){
				propertyNames += (propertyNames.equals("")?" ": ", ")
								  + refProperties[i].toString(); 
			}
		}
		
		if (propertyNames.equals("")){
			this.refProperties = refProperties;
		}else{
			DebugSystem.logError("The following value(s) of the constraint enum"
								+ " is/are prohibited as a foreign key property"
								+ ":" + propertyNames);
			this.refProperties = null;
		}		
	}
		
}
