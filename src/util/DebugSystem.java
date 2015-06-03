/**
 * Util package
 * Containing overall useful or general classes.
 */
package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Simple Debugging System. Allowing to print messages and errors.
 * @author Torsten Dietl
 * @since 2015-02-13
 * @version 0.1.0
 */
public final class DebugSystem {
	private static boolean bDebugFlag;
	private static boolean bFileFlag;
	private static String logFilepath = "";
	private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	
	/**
	 * Method to log the complete error information, 
	 * i.e. Message and Stack Trace.
	 * @param Exception The exception object, which is logged.
	 */
	public static void logError(Exception e){
		DebugSystem.logError(e.getMessage());
		DebugSystem.logError(e.getStackTrace());
	}
	
	
	/**
	 * Method to log an error message. "Error: " is prefixed.
	 * @param String Error message.
	 */
	public static void logError(String errMsg){
		if (bDebugFlag)
			print("[" + dateFormat.format(new Date()) + "] Error: " + errMsg);
	}
	
	/**
	 * Method to log the stack trace. "Error Stack Trace: " is prefixed.
	 * @param StackTraceElement[] The stack trace.
	 */
	public static void logError(StackTraceElement[] stack){
		StringBuilder sb = new StringBuilder("");
		if (bDebugFlag){
			sb.append("[" + dateFormat.format(new Date()) + "] Error Stack Trace: \n");
			for (int i=0; i<stack.length; i++){
				sb.append(stack[i] + "\n");
			}
			
			print(sb.toString());
		}
	}
	
	
	/**
	 * Method to log a user defined warning message. "Warning: " is prefixed.
	 * @param String The warning to log.
	 */
	public static void logWarning(String msg){
		if (bDebugFlag)
			print("[" + dateFormat.format(new Date()) + "] Warning: " + msg);
	}	
	

	/**
	 * Method to log a user defined message. "Debug: " is prefixed.
	 * @param String The message to log.
	 */
	public static void logMessage(String msg){
		if (bDebugFlag)
			print("[" + dateFormat.format(new Date()) + "] Debug: " + msg);
	}
	
	
	/**
	 * A simple visualization of the given ResultSet object.
	 * @param rs The ResultSet object, which should be "printed".
	 * @param showColumnNames If true, column names are included.
	 * @return String representation of the ResultSet.
	 */
	public static void printResultSet(ResultSet rs, boolean showColumnNames){
		StringBuilder sb;
		ResultSetMetaData rsmd;

		sb = new StringBuilder("");
		
		try {
			rsmd = rs.getMetaData();
			
			if (showColumnNames){
				for (int i=0; i<rsmd.getColumnCount(); i++){
					sb.append(i>0?"\t|\t":"");
					sb.append(rsmd.getColumnName(i));
				}
				
				sb.append("\r\n");
			}
				
			while (rs.next()){
				DebugSystem.logMessage("ColumnCount: " + rsmd.getColumnCount());
				for (int i=0; i<rsmd.getColumnCount(); i++){
					sb.append(i>0?"\t|\t":"");
					sb.append(rs.getObject(i).toString());
				}
			}			
		} catch (SQLException e) {
			DebugSystem.logError(e);
		}
		
		print(sb.toString());
	}
	
	
	/**
	 * A simple visualization of the given ResultSet object. Always includes 
	 * column names.
	 * @param rs The ResultSet object, which should be "printed".
	 * @return String representation of the ResultSet.
	 */
	public static void printResultSet(ResultSet rs){
		printResultSet(rs,true);
	}
	
	
	/**
	 * Prints the line either into a file or into the console,
	 * depending on the file flag.
	 * If the line cannot be written into the file, it automatically unsets
	 * the file flag and prints everything on the console.
	 * @param String The line, which should be printed.
	 */
	private static void print(String line){		
		// if we log into a file
		if (bFileFlag){
			// create necessary objects
			FileWriter writer;
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
			Date date = new Date();
			String tempFile = "log_" + dateFormat.format(date) + ".txt";
			
			// set the file path if no file was specified
			if (logFilepath.equals("")){ logFilepath = tempFile; }
			
			// try to write it in the file
			try {
				writer = new FileWriter(logFilepath, true);
				writer.write(line + "\r\n");
				writer.flush();
				writer.close();
			// if not possible unset the file flag, print the errors, 
			// a seperator and then line again.
			} catch (IOException e) {
				unsetFileFlag();
				logError(e);
				System.out.println("------------------------------------");
				System.out.println("");
				System.out.println(line);
			}
			
		// if not just print it on the console
		}else{
			System.out.println(line);
		}
	}
	
	
	/**
	 * Sets the filepath to the log file. If no filepath is specified, the 
	 * log is printed into the file log.txt in the application path.
	 * @param String Path to the log file.
	 */
	public static void setLogFilepath(String filepath){
		DebugSystem.logFilepath = new File(filepath).getAbsolutePath();
	}
	
	
	/**
	 * Sets the log file flag to true. Prints the errors and messages into a 
	 * log file.
	 */
	public static void setFileFlag(){	DebugSystem.bFileFlag = true; }
	
	/**
	 * Sets the log file flag to false. Prints the errors and messages onto the
	 * console.
	 */
	public static void unsetFileFlag(){ DebugSystem.bFileFlag = false; }
	
	/**
	 * Toggles the log file flag from true to false and vice versa.
	 */
	public static void toggleFileFlag(){ 
		DebugSystem.bFileFlag = !DebugSystem.bFileFlag; 
	}	
	
	
	/**
	 * Sets the debug flag to true. Enable error and message logging.
	 */
	public static void setDebugFlag(){	DebugSystem.bDebugFlag = true; }
	
	/**
	 * Sets the debug flag to false. Prevent error or message logging.
	 */
	public static void unsetDebugFlag(){ DebugSystem.bDebugFlag = false; }
	
	/**
	 * Toggles the debug flag from true to false and vice versa.
	 */
	public static void toggleDebugFlag(){ 
		DebugSystem.bDebugFlag = !DebugSystem.bDebugFlag; 
	}
}
