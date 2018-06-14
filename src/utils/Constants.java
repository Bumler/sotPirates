package utils;

import java.nio.file.Paths;

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
	
	public static class pathConstants {
		// The islands are stored in a local folder called images
		public static final java.nio.file.Path IMAGES_DESKTOP_ISLANDS_DIR = Paths.get("images/desktop/Islands");
		public static final java.nio.file.Path IMAGES_DESKTOP_MAPS_DIR = Paths.get("images/desktop/Maps");
		public static final java.nio.file.Path IMAGES_MOBILE_ISLANDS_DIR = Paths.get("images/mobile/Islands");
		public static final java.nio.file.Path IMAGES_MOBILE_MAPS_DIR = Paths.get("images/mobile/Maps");
	}
}
