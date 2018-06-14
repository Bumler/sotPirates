/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package EndPoints;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import utils.Constants.ParamConstants;
import utils.IslandManager;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import Exceptions.InvalidAttributeException;
import Resources.Island;
import app.IslandList;

@Api(value = "islands")
@SwaggerDefinition(info = @Info(title = "/islands", version = "1.0", description = "Endpoint to grab islands", license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"), contact = @Contact(name = "SoT Pirates", email = "sotpirates@gmail.com", url = "https://www.sotpirates.com")))
@Path("/islands")

/**
 * Class containing the island endpoints to sotpirates.
 * @author ryanpelaez
 *
 */
public class IslandEndpoints {

	List<Island> islands;

	public  IslandEndpoints() throws InvalidAttributeException {
		islands = IslandList.getIslands();
	}

	@GET
	@Path("/{islandName}")
	@Produces({ "application/json" })
	@ApiOperation(value = "Return the island with the given name", notes = "Returns HTTP 200 if the island is found.")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Valid island is found"),
			@ApiResponse(code = 404, message = "Valid island is not found") })

	public Response getIsland(@PathParam("islandName") String islandName) {

		IslandManager im = new IslandManager(islands);

		return im.getIsland(islandName);
	}

	@GET
	@Produces({ "application/json" })

	@ApiOperation(value = "Returns all islands", notes = "Returns HTTP 200 if island(s) are found.")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Valid island is found"),
			@ApiResponse(code = 404, message = "Valid island is not found") })

	public Response getIslands(
			@ApiParam(value = "Name of Island", required = false) @QueryParam(ParamConstants.NAME) String islandName,
			@ApiParam(value = "Filters for islands.", required = false) @QueryParam(ParamConstants.FILTERS) String filters,
			@ApiParam(value = "Determines if the filters are AND or OR.", required = false) @QueryParam(ParamConstants.EXCLUSIVE) String isExclusive) {

		IslandManager im = new IslandManager(islands);
		return im.getIslands(islandName, filters, isExclusive);
	}

	@GET
	@Path("/images/{islandName}")
	@Produces({ "image/jpeg,image/png" })
	@ApiOperation(value = "Return the JPG image of the island", notes = "Returns Input Stream if found")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Valid island is found"),
			@ApiResponse(code = 404, message = "Valid island is not found") })

	public InputStream getIslandImage(@PathParam("islandName") String islandName,
			@ApiParam(value = "Flag to determine if the image is of 'island' or 'map' view", required = false) @QueryParam(ParamConstants.IS_MAP) String isMap,
			@ApiParam(value = "Flag to determine if the request is coming from mobile or not", required = false) @QueryParam(ParamConstants.IS_MOBILE) String isMobile) {

		IslandManager im = new IslandManager(islands);

		return im.getIslandImage(islandName, isMap, isMobile);
	}

}
