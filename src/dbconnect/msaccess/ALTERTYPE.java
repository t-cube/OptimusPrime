/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

/**
 * Enumeration containing possible alteration types of an ALTER STATEMENT.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-25
 */
public enum ALTERTYPE {
	ADD (" ADD"),
	ALTER (" ALTER"),
	DROP (" DROP");
	
	private String type;
	ALTERTYPE(String type){ this.type = type; }
	public String toString(){ return this.type; }
}
