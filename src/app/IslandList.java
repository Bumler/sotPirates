package app;

import java.util.ArrayList;
import java.util.List;

import Exceptions.InvalidAttributeException;
import Resources.Island;
import utils.Constants.Attribute;
/**
 * Class containing all of the islands
 * @author ryanpelaez
 *
 */
public class IslandList {

	/**
	 * Get all of the islands 
	 * 
	 * @return
	 * @throws InvalidAttributeException 
	 */
	public static List<Island> getIslands() throws InvalidAttributeException {

		List<Island> islands = new ArrayList<>();

		// Alphabetized
		islands.add(new Island("Ancient Spire Outpost", Attribute.MULTIPLE, "V12", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Barnacle Cay", Attribute.SINGLE,"T19", Attribute.CHICKENS));
		islands.add(new Island("Black Sand Atoll", Attribute.MULTIPLE, "T3", Attribute.SNAKES));
		islands.add(new Island("Black Water Enclave", Attribute.MULTIPLE, "X5", Attribute.CHICKENS));
		islands.add(new Island("Blind Man's Lagoon", Attribute.SINGLE,"S6", Attribute.PIGS));
		islands.add(new Island("Booty Isle", Attribute.SINGLE,"N25", Attribute.SNAKES));
		islands.add(new Island("Boulder Cay", Attribute.SINGLE,"H5", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Cannon Cove", Attribute.SINGLE,"H11", Attribute.CHICKENS, Attribute.PIGS, Attribute.DOCKS));
		islands.add(new Island("Castaway Isle", Attribute.SINGLE,"M16/M17", Attribute.SNAKES));
		islands.add(new Island("Chicken Isle",Attribute.SINGLE, "K19", Attribute.CHICKENS));
		islands.add(new Island("Crescent Isle", Attribute.SINGLE,"B10/B11", Attribute.PIGS, Attribute.DOCKS, Attribute.SNAKES));
		islands.add(new Island("Crook's Hollow", Attribute.SINGLE,"Q19", Attribute.CHICKENS, Attribute.DOCKS, Attribute.SNAKES));
		islands.add(new Island("Cutlass Cay", Attribute.MULTIPLE, "Q22", Attribute.SNAKES));
		islands.add(new Island("Dagger Tooth Outpost", Attribute.SINGLE,"Q8/Q9", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Devil's Ridge",Attribute.SINGLE, "U24", Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("Discovery Ridge", Attribute.MULTIPLE, "E20/E21", Attribute.CHICKENS, Attribute.SNAKES));
		islands.add(new Island("Fools Lagoon", Attribute.MULTIPLE, "K17", Attribute.PIGS));
		islands.add(new Island("Galleon's Grave Outpost", Attribute.SINGLE,"X9", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Golden Sands Outpost", Attribute.MULTIPLE, "E12/F12", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Hidden Spring Keep", Attribute.SINGLE,"K9", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("Isle of the Last Words", Attribute.SINGLE,"S10", Attribute.SNAKES));
		islands.add(new Island("Keel Haul Fort", Attribute.SINGLE,"C7", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("Kraken Watchtower", Attribute.SINGLE,"O6", Attribute.FORT));
		islands.add(new Island("Kraken's Fall", Attribute.SINGLE,"X15", Attribute.PIGS, Attribute.DOCKS, Attribute.SNAKES));
		islands.add(new Island("Lagoon of Whispers", Attribute.MULTIPLE, "D15", Attribute.CHICKENS));
		islands.add(new Island("Liar's Backbone", Attribute.MULTIPLE, "Y13"));
		islands.add(new Island("Lone Cove", Attribute.SINGLE,"J6", Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("Lonely Isle",Attribute.SINGLE, "I9", Attribute.SNAKES));
		islands.add(new Island("Lookout Point",Attribute.SINGLE, "L25", Attribute.PIGS));
		islands.add(new Island("Lost Gold Fort", Attribute.SINGLE,"J21", Attribute.FORT));
		islands.add(new Island("Marauder's Arch", Attribute.SINGLE,"V3", Attribute.CHICKENS, Attribute.SNAKES));
		islands.add(new Island("Mermaid's Hideaway", Attribute.SINGLE,"B16", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Mutineer Rock", Attribute.MULTIPLE, "R24", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Old Boot Fort", Attribute.SINGLE,"P17", Attribute.FORT));
		islands.add(new Island("Old Faithful Isle", Attribute.SINGLE,"Q4", Attribute.CHICKENS, Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("Old Salts Atoll", Attribute.MULTIPLE, "G23", Attribute.CHICKENS, Attribute.DOCKS));
		islands.add(new Island("Paradise Spring",Attribute.SINGLE, "O21", Attribute.PIGS));
		islands.add(new Island("Picaroon Palms", Attribute.SINGLE,"K4", Attribute.SNAKES));
		islands.add(new Island("Plunder Outpost",Attribute.SINGLE, "M22", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Plunder Valley", Attribute.SINGLE,"H19", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Plunderer's Plight", Attribute.SINGLE,"V6/W6", Attribute.PIGS));
		islands.add(new Island("Rapier Cay", Attribute.MULTIPLE, "D9", Attribute.CHICKENS));
		islands.add(new Island("Rum Runner Isle", Attribute.MULTIPLE, "J11", Attribute.PIGS));
		islands.add(new Island("Sailors' Bounty", Attribute.MULTIPLE, "B4", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Sailor's Knot Stronghold", Attribute.SINGLE,"E17", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("Salty Sands", Attribute.MULTIPLE, "I3"));
		islands.add(new Island("Sanctuary Outpost",Attribute.MULTIPLE,  "F7/F8/G7/G8", Attribute.DOCKS, Attribute.OUTPOST));
		islands.add(new Island("Sandy Shallows", Attribute.MULTIPLE, "E5", Attribute.SNAKES));
		islands.add(new Island("Scurvy Isley", Attribute.SINGLE,"N4"));
		islands.add(new Island("Sea Dog's Rest", Attribute.SINGLE,"B13", Attribute.PIGS));
		islands.add(new Island("Shark Bait Cove", Attribute.MULTIPLE, "I24", Attribute.CHICKENS, Attribute.PIGS, Attribute.DOCKS));
		islands.add(new Island("Shark Fin Camp",Attribute.SINGLE, "U5", Attribute.FORT));
		islands.add(new Island("Shark Tooth Key", Attribute.MULTIPLE, "U15", Attribute.PIGS));
		islands.add(new Island("Shipwreck Bay", Attribute.MULTIPLE, "P12/Q12", Attribute.CHICKENS, Attribute.PIGS));
		islands.add(new Island("Shiver Retreat", Attribute.SINGLE,"V13", Attribute.PIGS));
		islands.add(new Island("Skull Keep", Attribute.MULTIPLE, "U11", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("Smugglers' Bay",  Attribute.SINGLE, "F2/F3", Attribute.CHICKENS));
		islands.add(new Island("Snake Island", Attribute.MULTIPLE, "M19/N19", Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("The Crooked Masts",  Attribute.SINGLE, "T13", Attribute.CHICKENS, Attribute.SNAKES));
		islands.add(new Island("The Crow's Nest Fortress",  Attribute.SINGLE, "S22", Attribute.DOCKS, Attribute.FORT));
		islands.add(new Island("The Sunken Grove", Attribute.MULTIPLE, "T8/U8", Attribute.PIGS, Attribute.SNAKES));
		islands.add(new Island("Thieves' Haven", Attribute.SINGLE,"P24/P25", Attribute.CHICKENS, Attribute.PIGS, Attribute.DOCKS));
		islands.add(new Island("Tri-Rock Isle",   Attribute.SINGLE,  "W11/X11", Attribute.CHICKENS));
		islands.add(new Island("Twin Groves", Attribute.MULTIPLE, "I13", Attribute.CHICKENS));
		islands.add(new Island("Wanderers Refuge", Attribute.SINGLE, "G15", Attribute.CHICKENS));
		
		return islands;
	}
}
