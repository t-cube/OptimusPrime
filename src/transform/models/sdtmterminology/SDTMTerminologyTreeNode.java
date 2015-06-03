/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example. In this case model object for the 
 * SDTM Terminology code lists.
 */
package transform.models.sdtmterminology;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class SDTMTerminologyTreeNode extends DefaultMutableTreeNode {
	/**
	 *  Serial UID is a Goedel number, with the partial version numbers as
	 *  power to the following bases: Major = 5, Minor = 3, Fixes = 2
	 *  --> Version 0.1.0 --> 5^0 * 3^1 * 2^0 = 1 * 3 * 1 = 3 
	 */
	private static final long serialVersionUID = 3L;

	private SDTMTerminology term;
	
	
	/**
	 * Simple constructor, setting the term of this node.
	 * @param term
	 */
	public SDTMTerminologyTreeNode(SDTMTerminology term){
		this.term = term;		
	}
	
	/**
	 * Returns either the code list name, if it is a codelist node, or the 
	 * submission value if it's a leaf of a codelist.
	 */
	public String toString(){
		if (this.term.getCodelistCode() == null)
			return this.term.getCodelistName();
		else
			return this.term.getSubmissionValue();
	}
	
	/**
	 * @return The SDTMTerminology object stored in this node.
	 */
	public SDTMTerminology getTerm(){
		return this.term;
	}
	
}
