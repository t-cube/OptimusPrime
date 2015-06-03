/**
 * Test package - Subpackage DBConnect
 * Containing all test classes for the connection wrapper classes.
 */
package tests.dbconnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnect.msaccess.DBConnectMSAccess;
import util.DebugSystem;

/**
 * Simple test class to check if a connection to a MS Access database can be 
 * established.
 * @author Torsten Dietl
 * @since 2015-02-17
 * @version 0.1.0
 */
public class Test_DBConnectMSAccess {

	/**
	 * Depends on a MS Access existing under the given path and containing the 
	 * table "tbl_test" with the columns (ID, integer).
	 * and (Test, Text).	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String dbPath = "I:/Ablage Torsten/Projekte/OptimusPrime/workspace/"
						+ "OptimusPrime/testFiles/dbConnectMSAccess.accdb";
		DBConnectMSAccess dbConnect = new DBConnectMSAccess();
		
		DebugSystem.setDebugFlag();
		DebugSystem.setFileFlag();
				
		dbConnect.setDbPath(dbPath);
		DebugSystem.logMessage("Connect to DB: " + dbConnect.getDbPath());
		
		if (dbConnect.connectToDatabase()){
			DebugSystem.logMessage("Connected (Yes/No): Yes");
			
			stmt = dbConnect.getPreparedStatement("SELECT * FROM tbl_test");
			
			try {
				rs = stmt.executeQuery();
				
				DebugSystem.logMessage("ID | Test");
				while(rs.next()){
					DebugSystem.logMessage(rs.getInt("ID") + " | " + 
										rs.getString("Test"));
				}
			} catch (SQLException e) {
				DebugSystem.logError(e.getMessage());
				DebugSystem.logError(e.getStackTrace());
			}	
			
			if (dbConnect.closeConnection()){
				DebugSystem.logMessage("Database closed.");
			}else{
				DebugSystem.logMessage("Could not close Database.");
			}
		}else{
			DebugSystem.logMessage(" Connected (Yes/No): No");
		}
		
	}

}
