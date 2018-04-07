package app;

import java.util.ArrayList;
import java.util.List;

import Resources.Attribute;
import Resources.Island;
import utils.Filter;

public class Initializer {

	static List<Island> islands;

	
	public static void main(String[] args) {
		
		//Get the list of islands
		islands = IslandList.getIslands();

		//Create the filters
		Attribute fort = new Attribute("fort", true);
		Attribute outpost = new Attribute("outpost", true);
		Attribute snakes = new Attribute("snakes", true);
		ArrayList<Attribute> filters = new ArrayList();

		//NOTE: Consider these to be filters that are "checked"
//		filters.add(fort);
//		filters.add(outpost);
		filters.add(snakes);

		
		//Send the islands and filters to be filtered
		List<Island> filteredIslands = Filter.filterIslands(islands, filters);
		
		//Check to see if any islands came back
		if(filteredIslands.isEmpty()) {
			System.out.print("No islands matching the criteria");
		}
		//Iterated through the islands that match the filter
		for (Island island : filteredIslands) {
			System.out.println("Matched Filter: " + island.toString());
		}
	}
}
