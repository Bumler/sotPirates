package Resources;

import org.json.JSONObject;

import utils.Constants.IslandConstants;

/**
 * Class used to contain properties about a given Island.
 * @author ryanpelaez
 *
 */
public class Island {

	private JSONObject islandProperties;

	private Attribute name;
	private Attribute location;
	private Attribute outpost;
	private Attribute fort;
	private Attribute snakes;
	private Attribute chickens;
	private Attribute pigs;
	

	/**
	 * Constructs an island with the following properties
	 * @param name
	 * @param location
	 * @param outpost 
	 * @param fort 
	 * @param snakes
	 * @param chickens 
	 * @param pigs
	 */
	public Island(String name, String location, String outpost, String fort, String snakes, String chickens, String pigs) {
		islandProperties = new JSONObject();
	 
		islandProperties.put(IslandConstants.NAME, name);
		islandProperties.put(IslandConstants.LOCATION, location);
		islandProperties.put(IslandConstants.OUTPOST, outpost);
		islandProperties.put(IslandConstants.FORT, fort);
		islandProperties.put(IslandConstants.SNAKES, snakes);
		islandProperties.put(IslandConstants.CHICKENS, chickens);
		islandProperties.put(IslandConstants.PIGS, pigs);
		
		this.name = new Attribute(IslandConstants.NAME, name);
		this.location = new Attribute(IslandConstants.LOCATION, location);
		this.outpost = new Attribute(IslandConstants.OUTPOST, outpost);
		this.fort = new Attribute(IslandConstants.FORT, fort);
		this.snakes = new Attribute(IslandConstants.SNAKES, snakes);
		this.chickens = new Attribute(IslandConstants.CHICKENS, chickens);
		this.pigs = new Attribute(IslandConstants.PIGS, pigs);

	}
	
	/**
	 * Returns the name of the island
	 * @return
	 */
	public String getIslandName() {
		return (String) this.name.getValue();
	}

	/**
	 * Returns the value of the attribute
	 * @param attribute
	 * @return Value of attribute
	 */
	public Object getAttribute(String attribute) {
		switch (attribute) {
		case IslandConstants.NAME:
			return this.name.getValue();
		case IslandConstants.LOCATION:
			return this.location.getValue();
		case IslandConstants.OUTPOST:
			return this.outpost.getValue();
		case IslandConstants.FORT:
			return this.fort.getValue();
		case IslandConstants.SNAKES:
			return this.snakes.getValue();
		case IslandConstants.CHICKENS:
			return this.chickens.getValue();
		case IslandConstants.PIGS:
			return this.pigs.getValue();
		default:
			return null;
		}
	}
	
	public JSONObject getAttributes() {
		return islandProperties;
	}
}
