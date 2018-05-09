package app;

import java.util.ArrayList;
import java.util.List;

import Resources.Island;
import utils.Constants.Attribute;

public class IslandList {

	/**
	 * Contains a hardcoded list of Islands in SoT
	 * 
	 * @return
	 */
	public static List<Island> getIslands() {

		List<Island> islands = new ArrayList<>();

		// Alphabetized
		islands.add(new Island("Ancient Spire Outpost", "V12", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Barnacle Cay", "T19", Attribute.CHICKENS));
		islands.add(new Island("Black Sand Atoll", "T3", Attribute.SNAKES));
		islands.add(new Island("Black Water Enclave", "X5", Attribute.CHICKENS));
		islands.add(new Island("Blind Man's Lagoon", "S6", Attribute.PIGS));
		islands.add(new Island("Booty Isle", "N25", Attribute.SNAKES));
		islands.add(new Island("Boulder Cay", "H5", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Cannon Cove", "H11", Attribute.CHICKENS, Attribute.PIGS, Attribute.DOCKS));
		islands.add(new Island("Castaway Isle", "M16/M17", Attribute.SNAKES));
		islands.add(new Island("Chicken Isle", "K19", Attribute.CHICKENS));
		islands.add(new Island("Crescent Isle", "B10/B11", Attribute.PIGS, Attribute.DOCKS, Attribute.SNAKES));
		islands.add(new Island("Crook's Hollow", "Q19", Attribute.CHICKENS, Attribute.DOCKS, Attribute.SNAKES));
		islands.add(new Island("Cutlass Cay", "Q22", Attribute.SNAKES));
		islands.add(new Island("Dagger Tooth Outpost", "Q8/Q9", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Devil's Ridge", "U24", Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("Discovery Ridge", "E20/E21", Attribute.CHICKENS, Attribute.SNAKES));
		islands.add(new Island("Fools Lagoon", "K17", Attribute.PIGS));
		islands.add(new Island("Galleon's Grave Outpost", "X9", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Golden Sands Outpost", "E12/F12", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Hidden Spring Keep", "K9", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("Isle of the Last Words", "S10", Attribute.SNAKES));
		islands.add(new Island("Keel Haul Fort", "C7", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("Kraken Watchtower", "O6", Attribute.FORT));
		islands.add(new Island("Kraken's Fall", "X15", Attribute.PIGS, Attribute.DOCKS, Attribute.SNAKES));
		islands.add(new Island("Lagoon of Whispers", "D15", Attribute.CHICKENS));
		islands.add(new Island("Liar's Backbone", "Y13"));
		islands.add(new Island("Lone Cove", "J6", Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("Lonely Isle", "I9", Attribute.SNAKES));
		islands.add(new Island("Lookout Point", "L25", Attribute.PIGS));
		islands.add(new Island("Lost Gold Fort", "J21", Attribute.FORT));
		islands.add(new Island("Marauder's Arch", "V3", Attribute.CHICKENS, Attribute.SNAKES));
		islands.add(new Island("Mermaid's Hideaway", "B16", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Mutineer Rock", "R24", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Old Boot Fort", "P17", Attribute.FORT));
		islands.add(new Island("Old Faithful Isle", "Q4", Attribute.CHICKENS, Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("Old Salts Atoll", "G23", Attribute.CHICKENS, Attribute.DOCKS));
		islands.add(new Island("Paradise Spring", "O21", Attribute.PIGS));
		islands.add(new Island("Picaroon Palms", "K4", Attribute.SNAKES));
		islands.add(new Island("Plunder Outpost", "M22", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Plunder Valley", "H19", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Plunderer's Plight", "V6/W6", Attribute.PIGS));
		islands.add(new Island("Rapier Cay", "D9", Attribute.CHICKENS));
		islands.add(new Island("Rum Runner Isle", "J11", Attribute.PIGS));
		islands.add(new Island("Sailors' Bounty", "B4", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Sailor's Knot Stronghold", "E17", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("Salty Sands", "I3"));
		islands.add(new Island("Sanctuary Outpost", "F7/F8/G7/G8", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Sandy Shallows", "E5", Attribute.SNAKES));
		islands.add(new Island("Scurvy Isley", "N4"));
		islands.add(new Island("Sea Dog's Rest", "B13", Attribute.PIGS));
		islands.add(new Island("Shark Bait Cove", "I24", Attribute.CHICKENS, Attribute.PIGS, Attribute.DOCKS));
		islands.add(new Island("Shark Fin Camp", "U5", Attribute.FORT));
		islands.add(new Island("Shark Tooth Key", "U15", Attribute.PIGS));
		islands.add(new Island("Shipwreck Bay", "P12/Q12", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Shiver Retreat", "V13", Attribute.PIGS));
		islands.add(new Island("Skull Keep", "U11", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("Smugglers' Bay", "F2/F3", Attribute.CHICKENS));
		islands.add(new Island("Snake Island", "M19/N19", Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("The Crooked Masts", "T13", Attribute.CHICKENS, Attribute.SNAKES));
		islands.add(new Island("The Crow's Nest Fortress", "S22", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("The Sunken Grove", "T8/U8", Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("Thieves' Haven", "P24/P25", Attribute.CHICKENS, Attribute.PIGS, Attribute.DOCKS));
		islands.add(new Island("Tri-Rock Isle", "W11/X11", Attribute.CHICKENS));
		islands.add(new Island("Twin Groves", "I13", Attribute.CHICKENS));
		islands.add(new Island("Wanderers Refuge", "G15", Attribute.CHICKENS));
		
		return islands;
	}
}
