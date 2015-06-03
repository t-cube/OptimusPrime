/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

import util.DebugSystem;

/**
 * Wrapper object for the field definitions. Storing simple informations about
 * the field and providing a string representation to be used in CREATE or ALTER
 * statements.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-02-26
 */
public class FieldDefinition {
	private String fieldName;
	private DATATYPE datatype;
	private int fieldSize = -1;	
	private boolean allowNull = true;
	
	
	
//- Constructor --------------------------------------------------------------//	
	
	/**
	 * Creates a simple field definition without a field size constraint.
	 * @param fieldName The name for the field.
	 * @param datatype The data type the field has.
	 */
	public FieldDefinition(String fieldName, DATATYPE datatype){	
		// check if the field name is valid and set it
		if (Validator.validateObjectName(fieldName)){
			this.fieldName = fieldName;
			this.datatype = datatype;	
		}else{
			DebugSystem.logError("The field name '" + fieldName + 
								 "' is prohibited.");
		}
	}
	
	
	/**
	 * Creates a simple field definition without a field size constraint 
	 * (only allowing null or not is specified).
	 * @param fieldName The name for the field.
	 * @param datatype The data type the field has.
	 * @param allowNull Are null fields allowed?
	 */
	public FieldDefinition(String fieldName, DATATYPE datatype, 
							boolean allowNull){
		// check if the field name is valid and set it
		if (Validator.validateObjectName(fieldName)){
			this.fieldName = fieldName;
			this.datatype = datatype;	
			this.allowNull = allowNull;
		}else{
			DebugSystem.logError("The field name '" + fieldName + 
								 "' is prohibited.");
		}
	}
	
	
	/**
	 * Creates a full field definition with field name, data type and size. 
	 * Only applicable for Memo and Text data types, will log a warning 
	 * otherwise. 
	 * @param fieldName The name for the field.
	 * @param datatype The data type the field has.
	 * @param fieldSize The size constraint for a character based field 
	 * 					(Memo/Text).
	 */
	public FieldDefinition(String fieldName, DATATYPE datatype, int fieldSize){
		// check if the field name is valid and set it
		if (Validator.validateObjectName(fieldName)){
			this.fieldName = fieldName;
			this.datatype = datatype;
			this.fieldSize = fieldSize;
		
			// log a warning for incompatible arguements: datatype and fieldSize
			if (!datatype.equals(DATATYPE.MEMO) && !datatype.equals(DATATYPE.TEXT)){
				DebugSystem.logWarning("Wrong constructor used for the field '" + 
									 fieldName + "'. Field size is only used for"
									 + " Memo and Text fields.");
			}
		}else{
			DebugSystem.logError("The field name '" + fieldName + 
					 "' is prohibited.");
		}
	}

	
	/**
	 * Creates a full field definition with field name, data type, size and null
	 * specification. 
	 * Only applicable for Memo and Text data types, will log a warning 
	 * otherwise. 
	 * @param fieldName The name for the field.
	 * @param datatype The data type the field has.
	 * @param fieldSize The size constraint for a character based field 
	 * 					(Memo/Text).
	 * @param allowNull Are null fields allowed?
	 */
	public FieldDefinition(String fieldName, DATATYPE datatype, int fieldSize, 
							boolean allowNull){
		// check if the field name is valid and set it
		if (Validator.validateObjectName(fieldName)){
			this.fieldName = fieldName;
			this.datatype = datatype;
			this.fieldSize = fieldSize;
			this.allowNull = allowNull;
		
			// log a warning for incompatible arguements: datatype and fieldSize
			if (!datatype.equals(DATATYPE.MEMO) && !datatype.equals(DATATYPE.TEXT)){
				DebugSystem.logWarning("Wrong constructor used for the field '" + 
									 fieldName + "'. Field size is only used for"
									 + " Memo and Text fields.");
			}
		}else{
			DebugSystem.logError("The field name '" + fieldName + 
					 "' is prohibited.");
		}
	}
	
	
	
//- Private Methods ----------------------------------------------------------//


//- Public Methods -----------------------------------------------------------//	
	
	/**
	 * Returns the field definition as a string to use it in create or alter 
	 * statements.
	 */
	public String toString(){
		String result = "";
		if (this.fieldName != null && this.datatype != null){		
			result = this.fieldName + this.datatype;
					
			if (datatype.equals(DATATYPE.MEMO) || datatype.equals(DATATYPE.TEXT)){
				result += "(" + fieldSize + ")";
			}
			
			result += (this.allowNull?" NULL":" NOT NULL");
		}else{
			DebugSystem.logError("Field attributes not set - "
								+ "Cannot return field definition string.");
		}
		
		return result;		
	}
	
	
	
//- Getters ------------------------------------------------------------------//
	
	/**
	 * @return The field name.
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @return The data type of the field.
	 */
	public DATATYPE getDatatype() {
		return datatype;
	}

	/**
	 * @return The size constraint of the field. 
	 * Only applicable for character based data types (TEXT/MEMO). 
	 * Returns -1 per default for other data types.
	 */
	public int getFieldSize() {
		return fieldSize;
	}

	
	/**
	 * @return True if null as value is allowed for this field.
	 */
	public boolean isAllowNull(){
		return allowNull;
	}
	
	
	
//- Setters ------------------------------------------------------------------//	
	
	/**
	 * @param fieldName The field name to set.
	 */
	public void setFieldName(String fieldName) {
		// check if the field name is valid and set it
		if (Validator.validateObjectName(fieldName)){		
			this.fieldName = fieldName;
		}else{
			DebugSystem.logError("The field name '" + fieldName + 
					 				"' is prohibited.");
		}
	}

	
	/**
	 * @param datatype The data type to set.
	 */
	public void setDatatype(DATATYPE datatype) {
		this.datatype = datatype;
	}

	
	/**
	 * @param fieldSize The field size to set. 
	 * Only applicable for character based data types (TEXT/MEMO).
	 * Can be set for other data types as well, but will generate a warning
	 * and will have no effect on the field definition string. 
	 */
	public void setFieldSize(int fieldSize) {
		if (this.datatype!=null){
			if (!this.datatype.equals(DATATYPE.MEMO) && 
				!this.datatype.equals(DATATYPE.TEXT)){
				DebugSystem.logWarning("Field size is only used for"
						 + " Memo and Text fields.");
			}
		}
		this.fieldSize = fieldSize;
	}
	
	
	/**
	 * @param allowNull If true, then null values are allowed for this field.
	 */
	public void setAllowNull(boolean allowNull){
		this.allowNull = allowNull;
	}
	
}
