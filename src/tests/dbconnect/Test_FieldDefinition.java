/**
 * 
 */
package tests.dbconnect;

import util.DebugSystem;
import dbconnect.msaccess.DATATYPE;
import dbconnect.msaccess.FieldDefinition;

/**
 * @author TDietl
 *
 */
public class Test_FieldDefinition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DebugSystem.setDebugFlag();
		
		DebugSystem.logMessage("Define first field");
		FieldDefinition fd1 = new FieldDefinition("test_field_1", 
													DATATYPE.BOOLEAN);
		DebugSystem.logMessage("------------------");

		DebugSystem.logMessage("Define second field");
		FieldDefinition fd2 = new FieldDefinition("f2", DATATYPE.TEXT, 255);
		DebugSystem.logMessage("------------------");

		DebugSystem.logMessage("Define third field");
		FieldDefinition fd3 = new FieldDefinition("f3", DATATYPE.AUTONUM, 5);
		DebugSystem.logMessage("------------------");

		DebugSystem.logMessage("Define forth field");
		FieldDefinition fd4 = new FieldDefinition("12ASD*", DATATYPE.DOUBLE);
		DebugSystem.logMessage("------------------");
		
		DebugSystem.logMessage("Print first field defintion");
		DebugSystem.logMessage(fd1.toString());
		DebugSystem.logMessage("------------------");
		
		DebugSystem.logMessage("Change first field defintion");
		fd1.setDatatype(DATATYPE.MEMO);
		fd1.setFieldSize(3000);
		DebugSystem.logMessage("Print first field defintion");
		DebugSystem.logMessage(fd1.toString());
		DebugSystem.logMessage("------------------");
		
		DebugSystem.logMessage("Print second field defintion");
		DebugSystem.logMessage(fd2.toString());
		DebugSystem.logMessage("------------------");
		
		DebugSystem.logMessage("Print third field defintion");
		DebugSystem.logMessage(fd3.toString());
		DebugSystem.logMessage("------------------");
		
		DebugSystem.logMessage("Print forth field defintion");
		DebugSystem.logMessage(fd4.toString());
		DebugSystem.logMessage("------------------");
	}

}
