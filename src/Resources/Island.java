package Resources;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import Exceptions.InvalidAttributeException;
import utils.Constants.Attribute;

/**
 * Class used to contain properties about a given Island.
 * 
 * @author ryanpelaez
 *
 */
public class Island {

	private String name;
	private String location;
	private List<Attribute> supportedAttributes = new ArrayList<>();
	private JSONObject islandProperties = new JSONObject();

	/**
	 * 
	 * Creates an Island Object.
	 * 
	 * @param islandName
	 *            - Name of the island
	 * @param singleOrMultiple
	 *            - SINGLE or MULTIPLE depending on whether or not this island is
	 *            broken down into multiple islands or is one giant one
	 * @param location
	 *            - Location of the island on the map
	 * @param attributes
	 *            - a list of attributes that this island has
	 * @throws InvalidAttributeException
	 *             - If the attribute passed in is invalid or in the wrong location
	 */
	public Island(String islandName, Attribute singleOrMultiple, String location, Attribute... attributes)
			throws InvalidAttributeException {

		this.name = islandName;
		this.location = location;

		checkAddIslandType(singleOrMultiple);

		checkAddAttributes(attributes);

		generateJSON();
	}

	/**
	 * Checks to see if the attribute being passed in is either single or multiple
	 * 
	 * @param singleOrMultiple
	 * @throws InvalidAttributeException
	 */
	private void checkAddIslandType(Attribute singleOrMultiple) throws InvalidAttributeException {
		if (isIslandTypeAttribute(singleOrMultiple)) {
			supportedAttributes.add(singleOrMultiple);
		} else {
			String error = "singleOrMultiple argument for island '" + name + "' is invalid. Must be SINGLE or MULTIPLE";
			throw new InvalidAttributeException(error);
		}
	}

	private void checkAddAttributes(Attribute... attributes) throws InvalidAttributeException {
		for (Attribute attribute : attributes) {
			if (!isIslandTypeAttribute(attribute)) {
				supportedAttributes.add(attribute);
			} else {
				String error = "Attribute SINGLE or MULTIPLE for island '" + name
						+ "' is not allowed as part of these attributes. Must be passed in directly through the singleOrMultiple argument in the constructor.";
				throw new InvalidAttributeException(error);
			}
		}
	}

	private void generateJSON() {

		Attribute[] allProperties = Attribute.values();

		islandProperties.put("NAME", this.name);
		islandProperties.put("LOCATION", this.location);

		for (Attribute attribute : allProperties) {
			islandProperties.put(String.valueOf(attribute), getAttribute(attribute));
		}
	}

	/**
	 * Determines if the Attribute is SINGLE or MULTIPLE
	 * 
	 * @param attribute
	 * @return <b>true</b> if attribute is SINGLE or MULTIPLE<br>
	 *         <b>false</b> if attribute is anything else
	 */
	private boolean isIslandTypeAttribute(Attribute attribute) {
		return (attribute.equals(Attribute.SINGLE) || attribute.equals(Attribute.MULTIPLE));
	}

	/**
	 * Returns the name of the island
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the location of this island
	 * 
	 * @return
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Returns whether or not the island contains the specified attribute
	 * 
	 * @param attribute
	 * @return <b>true</b> if island contains attribute<br>
	 *         <b>false</b> if island does not
	 */
	public boolean getAttribute(Attribute attribute) {
		for (Attribute islandAttribute : supportedAttributes) {
			if (islandAttribute == attribute) {
				return true;
			}
		}
		return false;
	}

	public JSONObject getIslandInfo() {
		return islandProperties;
	}
}
