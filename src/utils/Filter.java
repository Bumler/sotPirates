package utils;

import java.util.ArrayList;
import java.util.List;

import Resources.Island;
import utils.Constants.Attribute;

/**
 * Custom class used to filter Islands.
 * 
 * @author ryanpelaez
 *
 */
public class Filter {

	/**
	 * Send in a list of islands and filters and get back a list of islands that
	 * meet the criteria
	 * 
	 * @param islands
	 * @param filters
	 * @return filteredIslands - ArrayList<Island>
	 */
	public static List<Island> filterIslands(List<Island> islands, String islandName, List<Attribute> filters,
			boolean exclusiveSearch) {
		List<Island> filteredIslands = new ArrayList<>();

		for (Island island : islands) {
			if (matchesFilter(island, islandName, filters, exclusiveSearch)) {
				filteredIslands.add(island);
			}
		}
		return filteredIslands;
	}

	/**
	 * Checks to see which type of filter (inclusive or exclusive) search it is.
	 * Then determines if the island matches the filters.
	 * 
	 * @param island
	 * @param filters
	 * @param exclusiveSearch
	 * @return
	 */
	private static boolean matchesFilter(Island island, String islandName, List<Attribute> filters,
			boolean exclusiveSearch) {

		if (exclusiveSearch) {
			return matchesExclusive(island, islandName, filters);
		}
		return matchesInclusive(island, islandName, filters);
	}

	/**
	 * If one attribute does not match return false. The island name is an
	 * exception. Returns false only if the island does not contain the given name
	 * 
	 * @param island
	 * @param filters
	 * @return
	 */
	private static boolean matchesExclusive(Island island, String islandName, List<Attribute> filters) {

		if (islandName != null && !containsName(island, islandName)) {
			return false;
		}
		if (filters != null) {
			for (Attribute attribute : filters) {

				if (!island.getAttribute(attribute)) {
					return false;
				}

			}
		}

		return true;
	}

	/**
	 * if at least one attribute matches. return true
	 * 
	 * @param island
	 * @param filters
	 * @return
	 */
	private static boolean matchesInclusive(Island island, String islandName, List<Constants.Attribute> filters) {

		if (islandName != null && containsName(island, islandName)) {
			return true;
		}
		if (filters != null) {
			for (Constants.Attribute attribute : filters) {
				if (island.getAttribute(attribute)) {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Returns whether or not the island that came in the URL is contained in
	 * selected island name.
	 * 
	 * @param island
	 * @param islandName
	 * @return
	 */
	private static boolean containsName(Island island, String islandName) {
		return island.getName().toLowerCase().contains(islandName.toLowerCase());
	}
}
