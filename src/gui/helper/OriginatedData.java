/**
 * 
 */
package gui.helper;

/**
 * Just a wrapper for the list data, to store the origin information of the D&D
 * data.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-21
 */
public class OriginatedData {
	private String value;
	private String origin;
	
	public OriginatedData(String value, String origin){
		this.value = value;
		this.origin = origin;
	}

	/**
	 * @return The value of the data
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return The origin of the data
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param value The value of the data
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param origin The origin of the data
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String toString(){
		return this.value.toString();
	}
	
}
