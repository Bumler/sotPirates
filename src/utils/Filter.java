package utils;

import java.util.ArrayList;
import java.util.List;

import Resources.Attribute;
import Resources.Island;
import utils.Constants.IslandConstants;

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
	public static List<Island> filterIslands(List<Island> islands, List<Attribute> filters, boolean exclusiveSearch) {
		List<Island> filteredIslands = new ArrayList<>();

		for (Island island : islands) {
			if (matchesFilter(island, filters, exclusiveSearch)) {
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
	private static boolean matchesFilter(Island island, List<Attribute> filters, boolean exclusiveSearch) {
		if (exclusiveSearch) {
			return matchesExclusive(island, filters);
		}
		return matchesInclusive(island, filters);
	}

	/**
	 * If one attribute does not match return false.
	 * 
	 * @param island
	 * @param filters
	 * @return
	 */
	private static boolean matchesExclusive(Island island, List<Attribute> filters) {
		for (Attribute attribute : filters) {
			if (!island.getAttribute(attribute.getName()).equals(attribute.getValue())) {
				return false;
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
	private static boolean matchesInclusive(Island island, List<Attribute> filters) {
		for (Attribute attribute : filters) {
			if (attribute.getName().equals(IslandConstants.NAME)) {
				if (island.getIslandName().toLowerCase().contains(attribute.getValue().toString().toLowerCase())) {
					return true;
				}
			} else {
				if (island.getAttribute(attribute.getName()).equals(attribute.getValue())) {
					return true;
				}
			}
		}
		return false;
	}
}
