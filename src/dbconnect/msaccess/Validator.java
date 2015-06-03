/**
 * MS Access connection package. 
 * Providing wrapper classes to connect to ms access databases and mock up 
 * some interaction functionalities.
 */
package dbconnect.msaccess;

import java.util.regex.Pattern;

/**
 * Final class providing functions to validate names.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-03-25
 */
public final class Validator {

	/**
	 * Validates an object name like a table or a field name.
	 * @param objectName The name which should be validated.
	 * @return True if the name is valid.
	 */
	public static boolean validateObjectName(String objectName){
		String patternTableName = "(?iu)[a-z_]+[\\w_]*";
		return Pattern.matches(patternTableName, objectName);
	}
}
