/**
 * 
 */
package tests.dbconnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import util.DebugSystem;
import dbconnect.msaccess.CONSTRAINT;
import dbconnect.msaccess.ConstraintDefinition;
import dbconnect.msaccess.DATATYPE;
import dbconnect.msaccess.DBConnectMSAccess;
import dbconnect.msaccess.FieldDefinition;
import dbconnect.msaccess.TableDefinition;

/**
 * @author TDietl
 *
 */
public class Test_TableDefinition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DebugSystem.setDebugFlag();
		
		
		DebugSystem.logMessage("Creating Table Definition");
		TableDefinition td = new TableDefinition("Test_Table_Definition");
		
		DebugSystem.logMessage("Creating Field Definitions");
		FieldDefinition fd1 = new FieldDefinition("fld1", DATATYPE.AUTONUM);
		FieldDefinition fd2 = new FieldDefinition("fld2", DATATYPE.LONG, false);
		FieldDefinition fd3 = new FieldDefinition("fld3", DATATYPE.TEXT, 255);
		
		DebugSystem.logMessage("Creating Constraint Definitions");
		ConstraintDefinition cd1 = new ConstraintDefinition("pk_fld1", 
					CONSTRAINT.PRIMARY_KEY, new String[]{fd1.getFieldName()});
		ConstraintDefinition cd3 = new ConstraintDefinition("unique_fld2", 
				CONSTRAINT.UNIQUE, new String[]{fd2.getFieldName()});
		
		DebugSystem.logMessage("Adding Field Definitions");
		DebugSystem.logMessage("Fld1: " + td.addField(fd1));
		DebugSystem.logMessage("Fld2: " + td.addField(fd2));
		DebugSystem.logMessage("Fld3: " + td.addField(fd3));
		
		DebugSystem.logMessage("Adding Constraint Definitions");
		DebugSystem.logMessage("Cd1: " + td.addConstraint(cd1));
		DebugSystem.logMessage("Cd3: " + td.addConstraint(cd3));
				
		td.createTable();
		
		DebugSystem.logMessage("--------------------------------");
		DebugSystem.logMessage("Read out ResultSet");
		
		String dbPath = "I:/Ablage Torsten/Projekte/OptimusPrime/workspace/"
				+ "OptimusPrime/testFiles/dbConnectMSAccess.accdb";
		
		DBConnectMSAccess db = new DBConnectMSAccess(dbPath);
		PreparedStatement stmt = db.getPreparedStatement("SELECT * FROM tbl_create_Test");
		ResultSet rs;
		
		try {
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			TableDefinition td2 = new TableDefinition(rsmd);
			
			td2.createTable();
		} catch (SQLException e) {
			DebugSystem.logError(e);
		}
		
		db.closeConnection();
		
	}

}