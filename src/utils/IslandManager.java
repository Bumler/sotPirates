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

	}

	public Response getIslands(String filters) {
		List<Attribute> attributes = determineAttributes(filters);

		// Get the list of islands
		islands = IslandList.getIslands();

		// Send the islands and filters to be filtered
		List<Island> filteredIslands = Filter.filterIslands(islands, attributes);

		// Check to see if any islands came back
		if (filteredIslands.isEmpty()) {
			System.out.print("No islands matching the criteria");
		}
		// Iterated through the islands that match the filter
		for (Island island : filteredIslands) {
			System.out.println("Matched Filter: " + island.toString());
		}

		String responsePayload = islandListToJSON(filteredIslands);

		Map<String, String> responseHeaders = new HashMap<String, String>();
		responseHeaders.put(httpsConstants.CONTENT_TYPE_HEADER, httpsConstants.APP_JSON);

		return buildResponse(responseHeaders, 200, responsePayload);
	}

	private String islandListToJSON(List<Island> filteredIslands) {
		JSONObject islands = new JSONObject();

		for (Island island : filteredIslands) {
			islands.put(island.getIslandName(), island.getAttributes());
		}
		return islands.toString();
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

		for (String attribute : attributeArray) {
			String[] attributeProperties = attribute.split("=");
			attributesList.add(new Attribute(attributeProperties[0], attributeProperties[1]));
		}
		return attributesList;
	}

	/*
	 * build the jaxrs response
	 * 
	 * @param scimResponse
	 * 
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
