package app;

import java.util.ArrayList;
import java.util.List;

import Resources.Island;

public class IslandList {

	/**
	 * Contains a hardcoded list of Islands in SoT
	 * @return
	 */
	public static List<Island> getIslands() {

		List<Island> islands = new ArrayList<>();
		
		islands.add(new Island("Smugglers' Bay", "F2/F3", "false", "false", "false", "true", "false"));
		islands.add(new Island("Salty Islands", "I3", "false", "false", "false", "false", "false"));
		islands.add(new Island("Black Sand Atoll", "T3", "false", "false", "false", "false", "false"));
		islands.add(new Island("Marauder's Arch", "V3", "false", "false", "false", "true", "false"));
		islands.add(new Island("Old Faithful Isle", "Q4", "false", "false", "false", "true", "false"));
		islands.add(new Island("Scurvy Isley", "N4", "false", "false", "false", "false", "false"));
		islands.add(new Island("Picaroon Palms", "K4", "false", "false", "false", "false", "false"));
		islands.add(new Island("Sailors' Bounty", "B4", "false", "false", "false", "true", "false"));	
		islands.add(new Island("Sandy Hallows", "E5", "false", "false", "true", "false", "false"));
		islands.add(new Island("Boulder Cay", "H5", "false", "false", "false", "true", "false"));
		islands.add(new Island("Shark Fin Camp", "U5", "false", "true", "false", "false", "false"));
		islands.add(new Island("Black Water Enclave", "X5", "false", "false", "false", "true", "false"));
		islands.add(new Island("Plunderer's Plight", "V6/W6", "false", "false", "false", "false", "false"));
		islands.add(new Island("Blind Man's Lagoon", "S6", "false", "false", "false", "false", "false"));
		islands.add(new Island("Kraken Watchtower", "O6", "false", "true", "false", "false", "false"));
		islands.add(new Island("Lone Cove", "J6", "false", "false", "false", "false", "false"));
		islands.add(new Island("Keel Haul Fort", "C7", "false", "true", "false", "false", "false"));
		islands.add(new Island("Sanctuary Outpost", "F7/F8/G7/G8", "true", "false", "false", "false", "false"));
		islands.add(new Island("The Sunken Grove", "T8/U8", "false", "false", "false", "false", "false"));
		islands.add(new Island("Dagger Tooth Outpost", "Q8/Q9", "true", "false", "false", "false", "false"));
		islands.add(new Island("Rapier Cay", "D9", "false", "false", "false", "true", "false"));
		islands.add(new Island("Lonely Isle", "I9", "false", "false", "false", "false", "false"));
		islands.add(new Island("Hidden Spring Keep", "K9", "false", "true", "false", "false", "false"));
		islands.add(new Island("Galleon's Grave Outpost", "X9", "true", "false", "false", "false", "false"));
		islands.add(new Island("Isle of Last Words", "S10", "false", "false", "false", "false", "false"));
		islands.add(new Island("Crescent Isle", "B10/B11", "false", "false", "false", "false", "false"));
		islands.add(new Island("Cannon Cove", "H11", "false", "false", "false", "true", "false"));
		islands.add(new Island("Rum Runner Isle", "J11", "false", "false", "false", "false", "false"));
		islands.add(new Island("Skull Keep", "U11", "false", "true", "false", "false", "false"));
		islands.add(new Island("Shipwreck Bay", "P12/Q12", "false", "false", "false", "true", "false"));
		islands.add(new Island("Liar's Backbone", "Y13", "false", "false", "false", "false", "false"));
		islands.add(new Island("Shiver Retreat", "V13", "false", "false", "false", "false", "false"));
		islands.add(new Island("The Crooked Masts", "T13", "false", "false", "false", "true", "false"));
		islands.add(new Island("Twin Groves", "I13", "false", "false", "false", "true", "false"));
		islands.add(new Island("Sea Dog's Rest", "B13", "false", "false", "false", "false", "false"));
		islands.add(new Island("Kraken's Fall", "X15", "false", "false", "false", "false", "false"));
		islands.add(new Island("Shark Tooth Key", "U15", "false", "false", "false", "false", "false"));
		islands.add(new Island("Wanderer's Refuge", "G15", "false", "false", "false", "true", "false"));
		islands.add(new Island("Lagoon of Whispers", "D15", "false", "false", "false", "true", "false"));
		islands.add(new Island("Mermaid's Hideaway", "B16", "false", "false", "false", "true", "false"));
		islands.add(new Island("Castaway Isle", "M16/M17", "false", "false", "false", "false", "false"));
		islands.add(new Island("Old Boot Fort", "P17", "false", "true", "false", "false", "false"));
		islands.add(new Island("Fool's Lagoon", "K17", "false", "false", "false", "false", "false"));
		islands.add(new Island("Sailor's Knot Stronghold", "E17", "false", "true", "false", "false", "false"));
		islands.add(new Island("Barnacle Clay", "T19", "false", "false", "false", "true", "false"));
		islands.add(new Island("Crook's Holow", "Q19", "false", "false", "false", "true", "false"));
		islands.add(new Island("Snake Island", "M19/N19", "false", "false", "false", "false", "false"));
		islands.add(new Island("Chicken Isle", "K19", "false", "false", "false", "true", "false"));
		islands.add(new Island("Plunder Valley", "H19", "false", "false", "false", "true", "false"));
		islands.add(new Island("Ancient Spire Outpost", "V12", "true", "false", "false", "false", "false"));
		islands.add(new Island("Paradise Spring", "O21", "false", "false", "false", "false", "false"));
		islands.add(new Island("Lost Gold Fort", "J21", "false", "true", "false", "false", "false"));
		islands.add(new Island("Discovery Ridge", "E20/E21", "false", "false", "false", "true", "false"));
		islands.add(new Island("Plunder Outpost", "M22", "true", "false", "false", "false", "false"));
		islands.add(new Island("Cutlass Cay", "Q22", "false", "false", "false", "false", "false"));
		islands.add(new Island("The Crow's Nest Fortress", "S22", "false", "true", "false", "false", "false"));
		islands.add(new Island("Old Salts Atoll", "G23", "false", "false", "false", "true", "false"));
		islands.add(new Island("Shark Bait Cove", "I24", "false", "false", "false", "true", "false"));
		islands.add(new Island("Thieves' Haven", "P24/P25", "false", "false", "false", "true", "false"));
		islands.add(new Island("Mutineer Rock", "R24", "false", "false", "false", "true", "false"));
		islands.add(new Island("Devil's Ridge", "U24", "false", "false", "false", "false", "false"));
		islands.add(new Island("Booty Isle", "N25", "false", "false", "false", "false", "false"));
		islands.add(new Island("Lookout Point", "L25", "false", "false", "false", "false", "false"));
		islands.add(new Island("Try-Rock Isle", "W11/X11", "false", "false", "false", "true", "false"));
		return islands;
	}
}
