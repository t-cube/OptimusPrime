/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

import java.sql.Types;


/**
 * Simple final converter class. Converting SQL Types into the MS Access 
 * DATATYPE enum equivalent.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-25
 */
public final class DatatypeConversion {	
	
	/**
	 * Converts a SQL type into an MS Access DATATYPE enum equivalent.
	 * @param t The SQL type.
	 * @param autoIncrement Is the field with the given SQL type an auto 
	 * 						increment field
	 * @param currency Is the field with the given SQL type a currency field.
	 * @return The MS Access DATATYPE enum equivalent.
	 */
	public static DATATYPE toMSAccessType(int t, boolean autoIncrement, 
										boolean currency){
		switch (t){
		case Types.BIT:
			return DATATYPE.BOOLEAN;
		case Types.CHAR:
			return DATATYPE.TEXT;
		case Types.DOUBLE:
			return DATATYPE.DOUBLE;
		case Types.INTEGER:
			if (autoIncrement)
				return DATATYPE.AUTONUM;
			else
				return DATATYPE.LONG;
		case Types.LONGVARBINARY:
			return DATATYPE.OLE;
		case Types.LONGVARCHAR:
			return DATATYPE.MEMO;
		case Types.NUMERIC:
			if (currency)
				return DATATYPE.CURRENCY;
		case Types.OTHER:
			return DATATYPE.GUID;
		case Types.REAL:
			return DATATYPE.SINGLE;
		case Types.SMALLINT:
			return DATATYPE.INT;
		case Types.TIMESTAMP:
			return DATATYPE.DATETIME;
		case Types.TINYINT:
			return DATATYPE.BYTE;
		}	
		return null;
	}
}
