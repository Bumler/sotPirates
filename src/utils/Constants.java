package utils;

public class Constants {

	/**
	 * Constants (properties) related to an island
	 * 
	 * @author ryanpelaez
	 *
	 */
	
	public enum Attribute{
		DOCKS, CHICKENS, SNAKES, PIGS, FORT, OUTPOST
	}
	
	public static class ParamConstants {
		public static final String FILTERS = "filters";
		public static final String EXCLUSIVE = "isExclusive";
		public static final String IS_MAP = "isMap";
		public static final String IS_MOBILE = "isMobile";
		public static final String NAME = "name";
	}

	public static class httpsConstants {
		public static final String CONTENT_TYPE_HEADER = "Content-Type";
		public static final String APP_JSON = "application/json";

	}

	public static class IslandConstants {
		public static final String CHICKENS = "chickens";
		public static final String FORT = "fort";
		public static final String DOCKS = "docks";
		public static final String LOCATION = "location";
		public static final String NAME = "name";
		public static final String OUTPOST = "outpost";
		public static final String PIGS = "pigs";
		public static final String SNAKES = "snakes";
	}

}
