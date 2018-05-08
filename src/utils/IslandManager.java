package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

	/**
	 * Return all the island(s) based on the filters
	 * 
	 * @param filters
	 * @param isExclusive
	 * @return
	 */
	public Response getIslands(String filters, String isExclusive) {

		JSONObject responsePayload;

		// Check to see if there are filters. If there are none, assume we are grabbing
		// all islands
		if (filters != null && !filters.isEmpty()) {
			boolean exclusiveSearch = IslandHelper.determineExclusivity(isExclusive);
			// Check if the isExclusive parameter was passed. Assume we are
			// filtering exclusively
			if (isExclusive != null && isExclusive.isEmpty()) {
				exclusiveSearch = true;
			} else {
				exclusiveSearch = Boolean.parseBoolean(isExclusive);
			}
			List<Attribute> attributes = IslandHelper.determineAttributes(filters);
			// Send the islands and filters to be filtered
			List<Island> filteredIslands = Filter.filterIslands(islands, attributes, exclusiveSearch);

			responsePayload = IslandHelper.islandListToJSON(filteredIslands);
		} else {
			responsePayload = IslandHelper.islandListToJSON(islands);
		}

		Map<String, String> responseHeaders = new HashMap<String, String>();
		responseHeaders.put(httpsConstants.CONTENT_TYPE_HEADER, httpsConstants.APP_JSON);
		responseHeaders.put("Access-Control-Allow-Origin", "*");

		return buildResponse(responseHeaders, HttpsURLConnection.HTTP_OK, responsePayload.toString());
	}

	/**
	 * Return the specific island
	 * 
	 * @param islandName
	 * @return
	 */
	public Response getIsland(String islandName) {
		JSONObject responsePayload = new JSONObject();
		int responseCode = HttpsURLConnection.HTTP_OK;
		boolean islandFound = false;
		for (Island island : islands) {
			if (islandName.toLowerCase().equals(island.getIslandName().toLowerCase())) {
				responsePayload = island.getIslandInfo();
				islandFound = true;
			}
		}
		if (!islandFound) {
			responseCode = HttpsURLConnection.HTTP_NOT_FOUND;
			responsePayload.put("Error", "Could not find island with name: " + islandName);
			responsePayload.put("ResponseCode", responseCode);
		}

		Map<String, String> responseHeaders = new HashMap<String, String>();
		responseHeaders.put(httpsConstants.CONTENT_TYPE_HEADER, httpsConstants.APP_JSON);
		responseHeaders.put("Access-Control-Allow-Origin", "*");

		return buildResponse(responseHeaders, responseCode, responsePayload.toString());
	}

	// The islands are stored in a local folder called images
	private static final java.nio.file.Path IMAGES_ISLANDS_DIR = Paths.get("images/Islands");
	private static final java.nio.file.Path IMAGES_MAPS_DIR = Paths.get("images/Maps");

	/**
	 * 
	 * Return the PNG image of the island.<br>
	 * 
	 * <b>Important:</b>By default if the isMap attribute is <i>not</i> in the URL,
	 * it will assume it is looking for 'island' view.
	 * 
	 * @param islandName
	 * @param isMobile
	 * @param isMap <br>
	 * 	 -<i>True</i>: 'Map' View <br>
	 *   -<i>False</i>: 'island' View <br>
	 *   -<i>null</i>: 'island' View <br>
	 * @return Image of island
	 * @throws IOException
	 *             - Image is not found.
	 */
	public InputStream getIslandImage(String islandName, String isMap, String isMobile) {

		String islandPNG = getImageName(islandName, isMap);
		java.nio.file.Path dest = getImagePath(islandPNG, isMap);

		System.out.println(dest.toAbsolutePath());
		if (!Files.exists(dest)) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}

		try {
			return Files.newInputStream(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generates the PNG name for the island image. By default it assumes the image
	 * is of the 'island' view and not 'map'
	 * 
	 * @param islandName
	 * @param isMap
	 * @return "IslandImage">.png
	 */
	private static String getImageName(String islandName, String isMap) {

		StringBuilder islandPNG = new StringBuilder();

		if (isMap != null && isMap.equalsIgnoreCase("true")) {
			islandPNG.append("Map ");
		}
		islandPNG.append(islandName);
		islandPNG.append(".png");

		return islandPNG.toString();
	}

	private static java.nio.file.Path getImagePath(String islandPNG, String isMap) {
		if (isMap != null && isMap.equalsIgnoreCase("true")) {
			return IMAGES_MAPS_DIR.resolve(islandPNG);
		}
		return IMAGES_ISLANDS_DIR.resolve(islandPNG);
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
