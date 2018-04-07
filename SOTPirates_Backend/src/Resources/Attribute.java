package Resources;

/**
 * 
 * Custom class used for holding Attribute - Value pairs
 * @author ryanpelaez
 *
 */
public class Attribute {

	private String name;
	private Object value;

	/**
	 * Name of the attribute and it's value
	 * @param name
	 * @param value
	 */
	public Attribute(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
