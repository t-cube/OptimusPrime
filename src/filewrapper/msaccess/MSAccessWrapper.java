/**
 * Package contains wrapper for various file formats. In this case MS Access.
 */
package filewrapper.msaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import util.DebugSystem;
import filewrapper.FileWrapper;
import dbconnect.msaccess.DBConnectMSAccess;
import dbconnect.msaccess.TableDefinition;

/**
 * Wrapper class to retrieve data out of a MS Access file.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class MSAccessWrapper implements FileWrapper<String, Map<String, TableDefinition>> {
	
//- Constructor --------------------------------------------------------------//


//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//
	
	@Override
	public boolean hasNext() {
		// Not necessary in this case, as the MSAccessWrapper returns all tables
		return true;
	}

	@Override
	public void next() {
		// Not necessary in this case, as the MSAccessWrapper returns all tables
	}

	@Override
	public Map<String, TableDefinition> getData(String dbPath) {
		// return all tables as table definitions
		HashMap<String, TableDefinition> result = new HashMap<String, TableDefinition>();
		
		// TODO Auto-generated method stub	
		DBConnectMSAccess dbConnection = new DBConnectMSAccess(dbPath);
		
		for (String tblName : dbConnection.getTableList()){
			TableDefinition td;
			
			PreparedStatement stmt = dbConnection.getPreparedStatement(
													"SELECT * FROM " + tblName);
			try {
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				
				td = new TableDefinition(rsmd);							
				
				result.put(tblName, td);
			} catch (SQLException e) {
				DebugSystem.logError(e);
			}
		}
		
		dbConnection.closeConnection();
		
		return result;
	}

	
//- Getters ------------------------------------------------------------------//


//- Setters ------------------------------------------------------------------//
	
}
