/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example. In this case model object for the 
 * SDTM Terminology code lists.
 */
package transform.models.sdtmterminology;

/**
 * Class representing SDTM Terminology objects/records.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-09
 */
public class SDTMTerminology {
	public static enum KEYS{
		CODE,
		CODE_LIST_CODE,
		EXTENSIBLE,
		CODE_LIST_NAME,
		SUBMISSION_VALUE,
		CDISC_SYNONYMS,
		CDISC_DEFINITION,
		NCI_PREFERRED_TERM;
	}
	
	private String code;
	private String codeListCode;
	private boolean extensible;
	private String codeListName;
	private String submissionValue;
	private String[] CDISCSynonyms;
	private String CDISCDefinition;
	private String NCIPreferredTerm;
	
	
//- Constructor --------------------------------------------------------------//


//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//


//- Getters ------------------------------------------------------------------//	
	
	/**
	 * @return The code of this SDTM Terminology element.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return The code of the parent code list or null it it is a code list 
	 * itself.
	 */
	public String getCodelistCode() {
		return codeListCode;
	}
	/**
	 * @return True if the code list is extensible by proprietary terms.
	 */
	public boolean isExtensible() {
		return extensible;
	}
	/**
	 * @return The name of the code list.
	 */
	public String getCodelistName() {
		return codeListName;
	}
	/**
	 * @return The value used for a submission
	 */
	public String getSubmissionValue() {
		return submissionValue;
	}
	/**
	 * @return Possible synonyms of the submission value, which all reference 
	 * 			this submission value.
	 */
	public String[] getCDISCSynonyms() {
		return CDISCSynonyms;
	}
	/**
	 * @return The definition for the term by CDISC.
	 */
	public String getCDISCDefinition() {
		return CDISCDefinition;
	}
	/**
	 * @return The associated NCI preferred term.
	 */
	public String getNCIPreferredTerm() {
		return NCIPreferredTerm;
	}


//- Setters ------------------------------------------------------------------//
	
	/**
	 * @param code Code of the term, usually in this style: C######.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @param codelistCode Code for the code list, usually in the style: C######.
	 */
	public void setCodelistCode(String codelistCode) {
		if (codelistCode == null){
			this.codeListCode = codelistCode;
		}else{
			if (!codelistCode.equals("")){
				this.codeListCode = codelistCode;
			}
		}
	}
	/**
	 * @param extensible If true, the code list is extensible by proprietary 
	 * 						terms.
	 */
	public void setExtensible(boolean extensible) {
		this.extensible = extensible;
	}
	/**
	 * @param codelistName The name of the code list.
	 */
	public void setCodelistName(String codelistName) {
		this.codeListName = codelistName;
	}
	/**
	 * @param submissionValue The submission value used for this term.
	 */
	public void setSubmissionValue(String submissionValue) {
		this.submissionValue = submissionValue;
	}
	/**
	 * @param cDISCSynonyms Synonyms, which refer to this term.
	 */
	public void setCDISCSynonyms(String[] cDISCSynonyms) {
		CDISCSynonyms = cDISCSynonyms;
	}
	/**
	 * @param cDISCDefinition the cDISCDefinition to set
	 */
	public void setCDISCDefinition(String cDISCDefinition) {
		CDISCDefinition = cDISCDefinition;
	}
	/**
	 * @param nCIPreferredTerm the nCIPreferredTerm to set
	 */
	public void setNCIPreferredTerm(String nCIPreferredTerm) {
		NCIPreferredTerm = nCIPreferredTerm;
	}	
}
