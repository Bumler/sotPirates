package utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Resources.Attribute;
import Resources.Island;

public class IslandHelper {
	/**
	 * Converts a list of islands into a JSON
	 * 
	 * @param filteredIslands
	 * @return
	 */
	public static JSONObject islandListToJSON(List<Island> filteredIslands) {
		JSONObject islands = new JSONObject();
		islands.put("islandCount", filteredIslands.size());

		for (Island island : filteredIslands) {
			islands.put(island.getIslandName(), island.getIslandInfo());
		}
		return islands;
	}

	/**
	 * Checks whether the request will be inclusive or exclusive. <br>
	 * By default it is <b>inclusive</b>.
	 * 
	 * @param isExclusive
	 * @return
	 */
	public static boolean determineExclusivity(String isExclusive) {
		// Check if the isExclusive parameter was passed. Assume we are
		// filtering inclusively
		if (isExclusive != null && isExclusive.isEmpty()) {
			return false;
		} else {
			return Boolean.parseBoolean(isExclusive);
		}
	}

	/**
	 * Takes the parameters and converts them into Attribute objects
	 * 
	 * @param filters
	 * @return List of attributes
	 */
	public static List<Attribute> determineAttributes(String filters) {

		List<Attribute> attributesList = new ArrayList<>();

		String[] attributeArray = filters.split(",");
		String[] attributeProperties;
		for (String attribute : attributeArray) {
			attributeProperties = attribute.split(":");
			if (attributeProperties.length == 2) {
				attributesList.add(new Attribute(attributeProperties[0], attributeProperties[1]));
			}
		}
		return attributesList;
	}
}
