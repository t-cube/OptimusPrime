/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import util.DebugSystem;

/**
 * Simple wrapper object, allowing to establish a connection to a MS Access 
 * database.
 * @author Torsten Dietl
 * @since 2015-02-13
 * @version 0.1.0;
 */
public class DBConnectMSAccess {
	// Database settings
	private String dbPath = "";
	private String dbUser = "";
	private String dbPassword = "";
	
	// Connection object
	private Connection conn = null;
	
	
//- Constructor --------------------------------------------------------------//
	
	// Necessary to have the possibility of a constructor without parameters.
	/**
	 * Simple constructor, just returns an instance of the class. 
	 */
	public DBConnectMSAccess(){}
	
	
	/**
	 * Constructor returning an instance of the class and instantly connecting 
	 * to the given database.
	 * @param dbPath Path to the database.
	 */
	public DBConnectMSAccess(String dbPath){
		connectToDatabase(dbPath);
	}
	

//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//
	
	/**
	 * Connects to the database using the database path attribute.
	 * @return Boolean True if a connection is established
	 */
	public boolean connectToDatabase(){
		String database = "jdbc:odbc:DRIVER="
						+ "{Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
		if (!this.dbPath.equals("")){			
			database += this.dbPath + ";";
			
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");				
				this.conn = DriverManager.getConnection(database, this.dbUser, 
														this.dbPassword);
				return true;
			} catch (SQLException e) {
				DebugSystem.logMessage("Database URL: " + database);
				DebugSystem.logMessage("Database User: " + this.dbUser);
				DebugSystem.logMessage("Database Password: " + this.dbPassword);
				DebugSystem.logError(e.getMessage());
				DebugSystem.logError(e.getStackTrace());
				return false;
			} catch (ClassNotFoundException e) {
				DebugSystem.logError(e.getMessage());
				DebugSystem.logError(e.getStackTrace());
			}
		}
		return false;
	}
	
	
	/**
	 * Connects to the MS Access database given by the path. 
	 * @param dbPath Path to the MS Access database.
	 * @return Boolean True if a connection is established
	 */
	public boolean connectToDatabase(String dbPath){
		this.dbPath = dbPath;						
		return this.connectToDatabase();
	}
	
	
	/**
	 * @return Statement A new statement object using the connection.
	 */
	public PreparedStatement getPreparedStatement(String sql){
		try {			
			return this.conn.prepareStatement(sql);
		} catch (SQLException e) {
			DebugSystem.logError(e.getMessage());
			DebugSystem.logError(e.getStackTrace());
			return null;
		}
	}
	
	
	/**
	 * Returns the meta data object of the connection.
	 * @return The DatabaseMetaData object of the connection, used for getting 
	 * 		   a list of tables, etc.
	 */
	public DatabaseMetaData getMetaData(){
		try {
			return this.conn.getMetaData();
		} catch (SQLException e) {
			DebugSystem.logError(e);
		}
		
		return null;
	}
	
	
	/**
	 * Calls the getTableList function with false as argument.
	 * @return A list containing the table names
	 */
	public ArrayList<String> getTableList(){
		return getTableList(false);
	}
	
	
	/**
	 * Returns all table names. Either with system table or without, depending
	 * on the given argument.
	 * @param includeSystemTables If true system tables are included.
	 * @return A list containing the table names of the database.
	 */
	public ArrayList<String> getTableList(boolean includeSystemTables){
		ArrayList<String> result = new ArrayList<String>();
		
		if (this.conn == null){
			if (!this.connectToDatabase()){
				DebugSystem.logError("Cannot establish a connection to the "
						+ "database. Therefore cannot retrieve a table list.");
			}
		}else{
			String tableName;		
			DatabaseMetaData meta = this.getMetaData();
			ResultSet rs;
			
			try {
				rs = meta.getTables(null, null, "%", null);
	
				while (rs.next()){
					tableName = rs.getString(3);
					
					if (includeSystemTables || !tableName.startsWith("MSys")){
						result.add(tableName);
					}				
				}
			} catch (SQLException e) {
				DebugSystem.logError(e);
			}
		}
		
		return result;
	}
		
	
	/**
	 * Close the connection to the database, if established.
	 * @return 
	 */
	public boolean closeConnection(){
		if (conn != null){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				DebugSystem.logError(e.getMessage());
				DebugSystem.logError(e.getStackTrace());
				return false;
			}
		}		
		return true;
	}
	
	
//- Getters ------------------------------------------------------------------//
	
	/**
	 * @return String to the database we want to connect to.
	 */
	public String getDbPath() {
		return dbPath;
	}


//- Setters ------------------------------------------------------------------//
	
	/**
	 * @param dbPath Path to the database we want to connect to.
	 */
	public void setDbPath(String dbPath) {
		this.dbPath = new File(dbPath).getAbsolutePath();
	}
	
	
}
