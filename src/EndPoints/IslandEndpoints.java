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
import utils.Constants;
import utils.Constants.IslandConstants;
import utils.IslandManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Api(value = "islands")
@SwaggerDefinition(info = @Info(title = "/islands", version = "1.0", description = "Endpoint to grab islands", license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"), contact = @Contact(name = "SoT Pirates", email = "sotpirates@gmail.com", url = "https://www.sotpirates.com")))
@Path("/islands")

public class IslandEndpoints {

	@GET
	@Produces({ "application/json", "application/json" })
	
	@ApiOperation(value = "Return the user with the given id", notes = "Returns HTTP 200 if the user is found.")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Valid island is found"),
			@ApiResponse(code = 404, message = "Valid island is not found") })

	public Response getUser(
			@ApiParam(value = "Filters for islands.", required = false) @QueryParam(Constants.FILTERS) String filters,
			@ApiParam(value = "Determines if the filters are AND or OR.", required = false) @QueryParam(Constants.EXCLUSIVE) String exclusive) {

		IslandManager im = new IslandManager();

		return im.getIslands(filters, exclusive);
	}

}
