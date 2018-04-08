package utils;

import java.util.ArrayList;
import java.util.List;

import Resources.Attribute;
import Resources.Island;

/**
 * Custom class used to filter Islands. 
 * @author ryanpelaez
 *
 */
public class Filter {

	/**
	 * Send in a list of islands and filters and get back a list of islands that meet the criteria
	 * @param islands
	 * @param filters
	 * @return filteredIslands - ArrayList<Island>
	 */
	public static List<Island> filterIslands(List<Island> islands, List<Attribute> filters) {
		List<Island> filteredIslands = new ArrayList<>();
		
		for (Island island : islands) {
			if (matchesFilter(island, filters)) {
				filteredIslands.add(island);
			}
		}
		return filteredIslands;
	}
	/**
	 * Checks to see if the specific island matches the criteria.
	 * @param island
	 * @param filters
	 * @return True if Island Matches, False if it does not
	 */
	private static boolean matchesFilter(Island island, List<Attribute> filters) {
		boolean matchesFilter = true;

		for (Attribute attribute : filters) {
			if (island.getAttribute(attribute.getName()) != attribute.getValue()) {
				return false;
			}
		}
		return matchesFilter;
	}
}
