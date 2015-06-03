/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.DebugSystem;

/**
 * Wrapper class for a table definition. Stores the tablename, the included 
 * fields and constraints.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-23
 */
public class TableDefinition {
	private String tblName = "";
	private HashMap<String, FieldDefinition> fields = 
									new HashMap<String, FieldDefinition>();
	private HashMap<String, ConstraintDefinition> constraints = 
									new HashMap<String, ConstraintDefinition>();
	
	
	
//- Constructor --------------------------------------------------------------//
	
	/**
	 * Creates an empty table definition, only including the table name, but 
	 * no fields and constraints.
	 * @param tblName The name of the table.
	 */
	public TableDefinition(String tblName){
		if (Validator.validateObjectName(tblName)){
			this.tblName = tblName;
		}else{
			DebugSystem.logError("The table name '" + tblName 
								+ "' is prohibited.");
		}
	}
	
	
	/**
	 * Creates a filled table definition out of an ResultSet object.
	 * BE AWARE: Only can read out fields, datatypes and field sizes. All
	 * constraints like primary keys, unique fields, not nulls etc. are lost.
	 * @param rs The ResultSet object to read out.
	 */
	public TableDefinition(ResultSetMetaData rsmd){		
		try {			
			// add all fields
			for (int i=1; i<=rsmd.getColumnCount(); i++){
				String fieldName = rsmd.getColumnName(i);
				DATATYPE datatype = DatatypeConversion.toMSAccessType(
														rsmd.getColumnType(i), 
														rsmd.isAutoIncrement(i),
														rsmd.isCurrency(i));
				int fieldSize = rsmd.getColumnDisplaySize(i);
				
				FieldDefinition fld = null;
				
				if (datatype==DATATYPE.MEMO || datatype==DATATYPE.TEXT){
					fld = new FieldDefinition(fieldName, datatype, fieldSize);
				}else{
					fld = new FieldDefinition(fieldName, datatype);
				}
				
				if (fld != null){
					this.fields.put(fieldName, fld);
				}
			}			
			
		} catch (SQLException e) {
			DebugSystem.logError(e);
		}
		
	}
	
	
	
//- Private Methods ----------------------------------------------------------//


//- Public Methods -----------------------------------------------------------//
	
	/**
	 * Adds a field to the field list of this table definition.
	 * @param fieldDef The field definition to add
	 * @return True if adding the field definition was successful.
	 */
	public boolean addField(FieldDefinition fieldDef){
		if (!fields.containsKey(fieldDef.getFieldName())){
			fields.put(fieldDef.getFieldName(), fieldDef);
			return true;
		}else{
			DebugSystem.logError("The field name '" + fieldDef.getFieldName() 
								+ "' already exists in the definition of table"
								+ " '" + this.tblName + "'.");
		}
		return false;
	}
	
	
	/**
	 * Adds a constraint to the constraint list of this table definition.
	 * @param constraintDef The constraint definition to add.
	 * @return True if adding the constraint definition was successful.
	 */
	public boolean addConstraint(ConstraintDefinition constraintDef){
		if (!constraints.containsKey(constraintDef.getConstraintName())){
			constraints.put(constraintDef.getConstraintName(), constraintDef);
			return true;
		}else{
			DebugSystem.logError("The constraint name '"
								+ constraintDef.getConstraintName() 
								+ "' already exists in the definition of table"
								+ " '" + this.tblName + "'.");
		}
		return false;
	}
	
	
	/**
	 * Creates a CREATE TABLEStatement.
	 * @return String representation of the CREATE TABLE Statement.
	 */
	public String createTable(){
		if (this.tblName == null){
			DebugSystem.logError("The table name is not set. The table cannot "
								+ "be created");
		}else if (this.fields.size()==0){
			DebugSystem.logError("No field defined, but at least "
								+ "one field must be created.");
		}else{
			String sql = "CREATE TABLE " + this.tblName;
			
			for (int i=0; i<this.fields.size(); i++)
				sql += (i>0?", ":" (") 
					+ this.fields.values().toArray()[i].toString();
			
			for (int i=0; i<this.constraints.size(); i++)
				sql += ", CONSTRAINT " + this.constraints.values().toArray()[i].toString();
			
			sql += ")";

			DebugSystem.logMessage("Create table SQL: " + sql);
						
			return sql;
		}		
		
		return null;
	}
	
	
	/**
	 * Creates an ALTER TABLE Statement.
	 * @param type The type of ALTER statement.
	 * @return String representation of the ALTER TABLE Statement.
	 */
	public String[] alterTable(ALTERTYPE type){	
		ArrayList<String> sqlList = new ArrayList<String>();	
		
		if (this.tblName == null){
			DebugSystem.logError("The table name is not set. The table cannot "
					+ "be altered");
		}else if (this.fields.size()==0 && this.constraints.size()==0){
			DebugSystem.logError("No field or constraint defined to alter.");
		}else if (type == null){
			DebugSystem.logError("An alter type must be given to create an "
								+ "alter statement.");
		}else{		
			String sql = "ALTER TABLE " + this.tblName + type.toString();
			
			if (type == ALTERTYPE.ALTER){
				String tempSQL;				
				
				for (FieldDefinition fld: this.fields.values()){
					tempSQL = sql + " COLUMN " + fld.toString();
					sqlList.add(tempSQL);
				}
				
				for (ConstraintDefinition cd: this.constraints.values()){
					tempSQL = sql + " CONSTRAINT " + cd.toString(); 
					sqlList.add(tempSQL);
				}
				
			}else{
				boolean commaFlag = false;
				
				for (FieldDefinition fld: this.fields.values()){
					sql += (commaFlag?", COLUMN ":" COLUMN ") +  fld.toString();
					commaFlag = true;
				}
				
				for (ConstraintDefinition cd: this.constraints.values()){
					sql += (commaFlag?", CONSTRAINT ": " CONSTRAINT ") + cd.toString();
					commaFlag = true;
				}
				
				sqlList.add(sql);
			}			
			
		}
				
		return (String[]) sqlList.toArray();
	}


	/**
	 * @return The table name 
	 */
	public String getTblName() {
		return tblName;
	}


	/**
	 * @return The fields of the table
	 */
	public HashMap<String, FieldDefinition> getFields() {
		return fields;
	}


	/**
	 * @return The constraints of the table. 
	 * 			TableDefinitions created using the ResultSetMetaData will not
	 * 			contain any Constraints! 
	 */
	public HashMap<String, ConstraintDefinition> getConstraints() {
		return constraints;
	}


	/**
	 * @param tblName The table name.
	 */
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}


	/**
	 * @param fields The fields of the table.
	 */
	public void setFields(HashMap<String, FieldDefinition> fields) {
		this.fields = fields;
	}


	/**
	 * @param constraints The constraints for the table.
	 */
	public void setConstraints(HashMap<String, ConstraintDefinition> constraints) {
		this.constraints = constraints;
	}
	
}
