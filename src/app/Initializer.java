package app;

import java.util.List;

import org.wso2.msf4j.MicroservicesRunner;

import EndPoints.IslandEndpoints;
import Resources.Island;

public class Initializer {

	static List<Island> islands;

	public static void main(String[] args) {

		//Determine a port
		int port = 9099;
		MicroservicesRunner mr = new MicroservicesRunner(port);

		//Start up the service
		mr.deploy(new IslandEndpoints()).start();

	}
}
