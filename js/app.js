var sotPirates = angular.module('sotPirates', ['ngRoute', 'ui.bootstrap']);

sotPirates.config(['$routeProvider', function($routeProvider){
	$routeProvider
	.when('/gallery',{
		templateUrl: 'html/gallery.html',
		controller: 'galleryController'
	})
	.when('/map', {
		templateUrl: 'html/map.html',
		controller: 'mapController'
	})
	.otherwise({
		redirectTo: '/gallery'
	});

}]);

sotPirates.factory('sotEndpoints', function(){
	return{
		baseURL : "http://localhost:9099",
		setMapView : SetIslandImages
	}

	function SetIslandImages (island){
		island.mapView = this.baseURL.concat("/islands/images/").concat(island.name).concat("?isMap=true");
		island.vanityShot = this.baseURL.concat("/islands/images/").concat(island.name);
	}
});

sotPirates.factory('IslandFactory', function($location, sotEndpoints){
	islands = [];

	return {
		Islands : islands,
		UpdateFilteredIslands : updateFilteredIslands,
		MarkIsland : markIsland
	}

	function loadInitial (newIslands){
		newIslands.forEach(function(island){
			sotEndpoints.setMapView(island);
			island.showing = true;
			island.marked = false;

			islands.push(island);
		});
	}

	function updateFilteredIslands (filteredIslands){
		if (islands.length == 0)
			loadInitial(filteredIslands);
		
		else{
			hideAllIslands();
			debugger;
			showFiltered(filteredIslands);
		}
	}

	function hideAllIslands(){
		islands.forEach(function(island){
			island.showing = false;
		});
	}

	function showFiltered(filteredIslands){
		filteredIslands.forEach(function(filteredIsland){
			getIsland(filteredIsland).showing = true;
		});
	}

	function getIsland (targetIsland){
		for (i = 0; i < islands.length; i++)
			if (islands[i].name === targetIsland.name)
				return islands[i];
	}

	function markIsland (island, event){
		getIsland(island).marked = true;

		$location.path('/map');
	}
});

sotPirates.controller('islandModalController', function($uibModal, $scope, island, IslandFactory){
	$scope.island = island;

	$scope.MarkOnMap = function(island){
		IslandFactory.MarkIsland(island);

		this.$close();
	};
});

sotPirates.controller('galleryController', function($scope, $uibModal, IslandFactory){
	$scope.Islands = IslandFactory.Islands;
	$scope.MarkOnMap = IslandFactory.MarkIsland;

	$scope.showIsland = function(islandToShow){
		$uibModal.open({
			animation: true,
			templateUrl: 'html/islandModal.html',
			controller: 'islandModalController',
			resolve:{
				island : islandToShow
			}
		  });
	}
});

sotPirates.controller('mapController', function($scope, IslandFactory){
	$scope.Islands = IslandFactory.Islands;
});

sotPirates.controller('controlsController', function($scope, $http, $q, $location, sotEndpoints, IslandFactory){
	baseFilter = "/islands?exclusive=true&filters=";

	$scope.filters = 
		{chickens:false,
		snakes:false,
		pigs:false,
		outpost:false,
		fort:false,
		name:""};

	$scope.changeView = function(){
		if ($location.path() === '/gallery')
			$location.path('/map');
		else
			$location.path('/gallery');
	}

	function filterIslands (filter){
		var deferred = $q.defer();

		addressURL = sotEndpoints.baseURL.concat(filter);

		var req = {
			method: 'GET',
 			url: addressURL,
 			headers: {
   				'Content-Type': 'application/json'
 			}
 		}
		
		$http(req)
			.then(function (response) {
				var updatedIslands = updateSelectedIslands(response.data)
				deferred.resolve(updatedIslands);
			});
		
		return deferred.promise;
	}

	function updateSelectedIslands (newIslands){
		currentIslands = [];

		angular.forEach(newIslands, function(value, key) {
			currentIslands.push(value);
		});

		return currentIslands;
	}

	$scope.updateFilter = function() {
		filter = buildFilter();

		requestIslands(filter);
	}

	function buildFilter(){
		filter = baseFilter;
		
		filter = addToFilter(filter, "chickens", $scope.filters.chickens);
		filter = addToFilter(filter, "snakes", $scope.filters.snakes);
		filter = addToFilter(filter, "pigs", $scope.filters.pigs);

		filter = addToFilter(filter, "outpost", $scope.filters.outpost);
		filter = addToFilter(filter, "fort", $scope.filters.fort);

		filter = addToFilter(filter, "name", $scope.filters.name);

		filter = filter.trim(',');

		return filter;
	}

	function addToFilter(filter, label, value){
		if (value || value != "")
			filter = filter.concat(label + ':' + value + ',');

		return filter;
	}

	function requestIslands (filter){
		promise = filterIslands(filter);
		promise.then(function(newIslands){
			IslandFactory.UpdateFilteredIslands(newIslands);
		});
	}

	function loadInitialIslands(){
		filter = baseFilter;

		requestIslands(filter);
	}

	loadInitialIslands();
});