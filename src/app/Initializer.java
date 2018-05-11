package app;

import java.util.List;

import org.wso2.msf4j.MicroservicesRunner;

import EndPoints.IslandEndpoints;
import Exceptions.InvalidAttributeException;
import Resources.Island;

/**
 * Main class to deploy the service
 * @author ryanpelaez
 *
 */
public class Initializer {

	static List<Island> islands;

	public static void main(String[] args) throws InvalidAttributeException {

		// Determine a port
		int port = 9099;
		MicroservicesRunner mr = new MicroservicesRunner(port);

		// Start up the service
		mr.deploy(new IslandEndpoints()).start();

	}
}
