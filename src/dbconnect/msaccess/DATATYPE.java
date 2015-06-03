/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

/**
 * MS Access datatype enumeration. 
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-25
 */
public enum DATATYPE {
	BOOLEAN (" BIT"),
	BYTE (" BYTE"),
	INT (" SMALLINT"),
	LONG (" INTEGER"),
	SINGLE (" REAL"),
	DOUBLE (" FLOAT"),
	CURRENCY (" MONEY"),
	AUTONUM (" COUNTER"),
	DATETIME (" DATETIME"),
	TEXT (" CHAR"),
	MEMO (" MEMO"),
	OLE (" IMAGE"),
	GUID (" GUID");
	
	private String type;
	
	DATATYPE(String type){ this.type = type; }
	
	public String toString(){ return this.type;	}
	
	/**
	 * @return True, if the datatype is a MEMO or an TEXT datatype.
	 */
	public static boolean isText(DATATYPE dt){ 
		return (dt.equals(DATATYPE.MEMO) || dt.equals(DATATYPE.TEXT)); 
	}
}
