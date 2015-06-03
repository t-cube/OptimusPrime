/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

/**
 * Enumeration containing constraint tokens possible in a CONSTRAINT clause.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-25
 */
public enum CONSTRAINT {
	PRIMARY_KEY (" PRIMARY KEY"),
	UNIQUE (" UNIQUE"),
	FOREIGN_KEY (" FOREIGN KEY"),
	ON_UPDATE (" ON UPDATE CASCADE"),
	ON_DELETE (" ON DELETE CASCADE"),
	SET_NULL (" SET NULL");	
	
	private String constraint;
	
	CONSTRAINT(String constraint){ this.constraint = constraint; }
	
	public String toString(){ return constraint; }
}
