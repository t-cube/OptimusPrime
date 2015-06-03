/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example.
 */
package transform.models.targetstructures.sdtm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SDTMDomain {	
	/**
	 * Possible mapping keys.
	 */
	public enum KEYS{
		ASSUMPTION,
		SPECIFICATION;		
	}
	
	private String domainName;
	private String assumption;
	private Map<String, SDTMDomainRecord> specification;
	
	
//- Constructor --------------------------------------------------------------//
	
	/**
	 * Simple constructor instantiating the hash map.
	 */
	public SDTMDomain(){
		this.specification = new HashMap<String, SDTMDomainRecord>();
	}
	

//- Private Methods ----------------------------------------------------------//
	
	
//- Public Methods -----------------------------------------------------------//


//- Getters ------------------------------------------------------------------//
	
	/**
	 * @return The name of the domain.
	 */
	public String getDomainName() {
		return domainName;
	}
	
	/**
	 * @return The assumptions for this domain.
	 */
	public String getAssumption() {
		return assumption;
	}
	
	/**
	 * @return The specification records for this domain. Mapped by their 
	 * 			variable name. 
	 */
	public Map<String, SDTMDomainRecord> getSpecification() {
		return specification;
	}


//- Setters ------------------------------------------------------------------//
	
	/**
	 * @param domainName The name of the domain.
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	/**
	 * @param assumption The assumption for the domain.
	 */
	public void setAssumption(String assumption) {
		this.assumption = assumption;
	}
	
	/**
	 * @param specification The specification records for this domain, mapped by
	 * 						their variable name.
	 */
	public void setSpecification(Map<String, SDTMDomainRecord> specification) {
		this.specification = specification;
	}
	
}
