package Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import org.json.JSONObject;

import utils.Constants;
import utils.Constants.Attribute;
import utils.Constants.IslandConstants;

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

	public Island(String islandName, String location, Attribute... attributes) {
		this.name = islandName;
		this.location = location;

		for (Attribute attribute : attributes) {
			supportedAttributes.add(attribute);
		}
		
		generateJSON();
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
	 * Returns the name of the island
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public String getLocation() {
		return this.location;
	}

	/**
	 * Returns the value of the attribute
	 * 
	 * @param attribute
	 * @return Value of attribute
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
