package utils;

/**
 * Constants used throughout this product.
 * @author ryanpelaez
 *
 */
public class Constants {

	/**
	 * ENUMS that are ways to uniquely identify characteristics of an island.
	 * @author ryanpelaez
	 *
	 */
	public enum Attribute{
		DOCKS, CHICKENS, SNAKES, PIGS, FORT, OUTPOST, SINGLE, MULTIPLE
	}
	
	/**
	 * Constants for QueryParms 
	 * @author ryanpelaez
	 *
	 */
	public static class ParamConstants {
		public static final String FILTERS = "filters";
		public static final String EXCLUSIVE = "isExclusive";
		public static final String IS_MAP = "isMap";
		public static final String IS_MOBILE = "isMobile";
		public static final String NAME = "name";
	}

	/**
	 * Constants used that are http related
	 * @author ryanpelaez
	 *
	 */
	public static class httpsConstants {
		public static final String CONTENT_TYPE_HEADER = "Content-Type";
		public static final String APP_JSON = "application/json";

	}
}
