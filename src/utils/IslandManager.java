package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.json.JSONObject;

import Resources.Attribute;
import Resources.Island;
import app.IslandList;
import utils.Constants.httpsConstants;

public class IslandManager {

	static List<Island> islands;

	public IslandManager() {
		// Get the list of islands
		islands = IslandList.getIslands();
	}

	public Response getIslands(String filters, String isExclusive) {

		JSONObject responsePayload;

		// Check to see if there are filters. If there are none, assume we are grabbing
		// all islands
		if (filters != null && !filters.isEmpty()) {
			boolean exclusiveSearch = determineExclusivity(isExclusive);
			// Check if the isExclusive parameter was passed. Assume we are
			// filtering exclusively
			if (isExclusive != null && isExclusive.isEmpty()) {
				exclusiveSearch = true;
			} else {
				exclusiveSearch = Boolean.parseBoolean(isExclusive);
			}
			List<Attribute> attributes = determineAttributes(filters);
			// Send the islands and filters to be filtered
			List<Island> filteredIslands = Filter.filterIslands(islands, attributes, exclusiveSearch);

			responsePayload = islandListToJSON(filteredIslands);
		} else {
			responsePayload = islandListToJSON(islands);
		}

		Map<String, String> responseHeaders = new HashMap<String, String>();
		responseHeaders.put(httpsConstants.CONTENT_TYPE_HEADER, httpsConstants.APP_JSON);
		responseHeaders.put("Access-Control-Allow-Origin", "*");

		return buildResponse(responseHeaders, 200, responsePayload.toString());
	}

	/**
	 * Converts a list of islands into a JSON
	 * 
	 * @param filteredIslands
	 * @return
	 */
	private JSONObject islandListToJSON(List<Island> filteredIslands) {
		JSONObject islands = new JSONObject();
		islands.put("islandCount", filteredIslands.size());

		for (Island island : filteredIslands) {
			islands.put(island.getIslandName(), island.getAttributes());
		}
		return islands;
	}

	/**
	 * Checks whether the request will be inclusive or exclusive)
	 * 
	 * @param isExclusive
	 * @return
	 */
	private boolean determineExclusivity(String isExclusive) {
		// Check if the isExclusive parameter was passed. Assume we are
		// filtering exclusively
		if (isExclusive != null && isExclusive.isEmpty()) {
			return true;
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
	private List<Attribute> determineAttributes(String filters) {

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

	/**
	 * Create a response to send back the user.
	 * 
	 * @param responseHeaders
	 * @param responseCode
	 * @param responsePayload
	 * @return
	 */
	public Response buildResponse(Map<String, String> responseHeaders, int responseCode, String responsePayload) {

		// create a response builder with the status code of the response to be
		// returned.
		Response.ResponseBuilder responseBuilder = Response.status(responseCode);

		if (responseHeaders != null && !responseHeaders.isEmpty()) {
			for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {

				responseBuilder.header(entry.getKey(), entry.getValue());
			}
		}
		// set the payload of the response, if available.
		if (responsePayload != null) {
			responseBuilder.entity(responsePayload);
		}
		return responseBuilder.build();
	}
}
