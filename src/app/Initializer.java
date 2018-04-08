package app;

import java.util.ArrayList;
import java.util.List;

import org.wso2.msf4j.MicroservicesRunner;

import EndPoints.IslandEndpoints;
import Resources.Attribute;
import Resources.Island;
import utils.Filter;

public class Initializer {

	static List<Island> islands;

	public static void main(String[] args) {

		int port = 9099;
		MicroservicesRunner mr = new MicroservicesRunner(port);
		
		mr.deploy(new IslandEndpoints()).start();

	}
}
