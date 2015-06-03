/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example.
 */
package transform.models.targetstructures.sdtm;

/**
 * Class, which represents one specification record for the sdtm specifications.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SDTMDomainRecord {
	
	/**
	 * The possible Keys for the SDTM specifications.
	 */
	public enum KEYS{
		ASSUMPTIONS,
		VARIABLE_NAME,
		VARIABLE_LABEL,
		TYPE,
		CODE_LIST,
		ROLE,
		CDISC_NOTES,
		CORE;
	}
	
	private String variableName;
	private String variableLabel;
	private String type;
	private String codeList;
	private String role;
	private String cdiscNotes;
	private String core;
	
	
//- Constructor --------------------------------------------------------------//
	

//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//	
	
	/**
	 * Returns the variable name of the specification record.
	 */
	public String toString(){
		return this.variableName;
	}


//- Getters ------------------------------------------------------------------//	
	
	/**
	 * @return The variable name of this specification record.
	 */
	public String getVariableName() {
		return variableName;
	}
	
	/**
	 * @return The variable label of this specification record.
	 */
	public String getVariableLabel() {
		return variableLabel;
	}
	
	/**
	 * @return The type used for this variable, defined in the 
	 *			specification record.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return The code list used for this variable, defined in the 
	 *			specification record.
	 */
	public String getCodeList() {
		return codeList;
	}
	
	/**
	 * @return The role used for this variable, defined in the 
	 *			specification record.
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * @return The cdisc notes for this variable, defined in the 
	 *			specification record.
	 */
	public String getCdiscNotes() {
		return cdiscNotes;
	}
	
	/**
	 * @return The core for this variable, defined in the 
	 *			specification record.
	 */
	public String getCore() {
		return core;
	}


//- Setters ------------------------------------------------------------------//	
	
	/**
	 * @param variableName The name of the variable.
	 */
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	/**
	 * @param variableLabel The label for the variable.
	 */
	public void setVariableLabel(String variableLabel) {
		this.variableLabel = variableLabel;
	}
	
	/**
	 * @param type The type of the variable, defined in the 
	 *			specification record.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @param codeList The code list of the variable, defined in the 
	 *			specification record.
	 */
	public void setCodeList(String codeList) {
		this.codeList = codeList;
	}
	
	/**
	 * @param role The role of the variable, defined in the 
	 *			specification record.
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * @param cdiscNotes The cdisc notes for the variable, defined in the 
	 *			specification record.
	 */
	public void setCdiscNotes(String cdiscNotes) {
		this.cdiscNotes = cdiscNotes;
	}
	
	/**
	 * @param core The core of the variable, defined in the 
	 *			specification record.
	 */
	public void setCore(String core) {
		this.core = core;
	}
	
}
