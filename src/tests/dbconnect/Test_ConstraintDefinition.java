/**
 * 
 */
package tests.dbconnect;

import dbconnect.msaccess.CONSTRAINT;
import dbconnect.msaccess.ConstraintDefinition;
import util.DebugSystem;

/**
 * @author TDietl
 *
 */
public class Test_ConstraintDefinition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DebugSystem.setDebugFlag();
		
		DebugSystem.logMessage("Define first constraint");
		ConstraintDefinition cd1 = new ConstraintDefinition("c2", 
												CONSTRAINT.PRIMARY_KEY, 
												new String[]{"f1", "f2", "f3"});
		DebugSystem.logMessage("------------------");

		DebugSystem.logMessage("Define second constraint");
		ConstraintDefinition cd2 = new ConstraintDefinition("c3", 
												CONSTRAINT.UNIQUE, 
												new String[]{"f1", "f2", "f3"},
												"test_tbl", 
												new String[]{"a", "b"},
												new CONSTRAINT[]{
													CONSTRAINT.ON_UPDATE});
		DebugSystem.logMessage("------------------");
		
		DebugSystem.logMessage("Print first constraint defintion");
		DebugSystem.logMessage(cd1.toString());
		DebugSystem.logMessage("------------------");
		
		DebugSystem.logMessage("Change first constraint defintion");
		cd1.setConstraintName("Neuer_Name");
		cd1.setConstraint(CONSTRAINT.FOREIGN_KEY);
		cd1.setFieldNames(new String[]{"f1"});
		cd1.setRefTableName("refTable");
		cd1.setRefTableFieldList(new String[]{"f2"});
		cd1.setRefProperties(new CONSTRAINT[]{});
		DebugSystem.logMessage("Print first constraint defintion");
		DebugSystem.logMessage(cd1.toString());
		DebugSystem.logMessage("------------------");
		
		DebugSystem.logMessage("Print second constraint defintion");
		DebugSystem.logMessage(cd2.toString());
		DebugSystem.logMessage("------------------");
		
	}

}
