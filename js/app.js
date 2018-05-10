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
		setMapView : SetIslandImages,
		GET : formatGetRequest
	}

	function SetIslandImages (island){
		island.mapView = this.baseURL.concat("/islands/images/").concat(island.NAME).concat("?isMap=true");
		island.vanityShot = this.baseURL.concat("/islands/images/").concat(island.NAME);
	}

	function formatGetRequest(body){
		addressURL = this.baseURL.concat(body);

		var req = {
			method: 'GET',
 			url: addressURL,
 			headers: {
   				'Content-Type': 'application/json'
 			}
		 };
		 console.log(addressURL);
		 return req;
	}
});

sotPirates.factory('IslandFactory', function($location, sotEndpoints){
	islands = {};

	return {
		Islands : islands,
		UpdateFilteredIslands : updateFilteredIslands,
		MarkIsland : markIsland
	}

	function loadInitial (newIslands){
		for (islandName in newIslands){
			island = newIslands[islandName];

			sotEndpoints.setMapView(island);
			island.showing = true;
			island.marked = false;

			islands[islandName] = island;
		}
	}

	function updateFilteredIslands (filteredIslands){
		if (Object.keys(islands).length === 0)
			loadInitial(filteredIslands);
		
		else{
			hideAllIslands();
			showFiltered(filteredIslands);
		}
	}

	function hideAllIslands(){
		for (island in islands)
			islands[island].showing = false;
	}

	function showFiltered(filteredIslands){
		for (island in filteredIslands)
			islands[island].showing = true;
	}

	function markIsland (island, event){
		islands[island.NAME].marked = true;

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
	baseFilter = "/islands?";

	$scope.name = "";
	$scope.isExclusive = true;
	$scope.filters = 
		{chickens:false,
		snakes:false,
		pigs:false,
		outpost:false,
		fort:false};

	$scope.changeView = function(){
		if ($location.path() === '/gallery')
			$location.path('/map');
		else
			$location.path('/gallery');
	}

	$scope.updateFilter = function() {
		filter = baseFilter;

		filter = filter.concat(NameParam()).concat("&");
		filter = filter.concat(FilterParam()).concat("&");
		filter = filter.concat(ExclusiveParam());
		
		requestIslands(filter);
	}

	function NameParam(){
		if ($scope.name.length === 0)
			return "";
		
		else{
			header = "name=";
			return header.concat($scope.name);
		}
	}

	function FilterParam(){
		filterParam = "filters=";

		for (filter in $scope.filters){
			if ($scope.filters[filter])
				filterParam = filterParam.concat(filter).concat(",");
		}

		return filterParam;
	}

	function ExclusiveParam(){
		header = "isExclusive=";
		return header.concat($scope.isExclusive);
	}

	function requestIslands (filter){
		promise = filterIslands(filter);
		promise.then(function(newIslands){
			IslandFactory.UpdateFilteredIslands(newIslands);
		});
	}

	function filterIslands (filter){
		var deferred = $q.defer();
		var req = sotEndpoints.GET(filter);
		
		$http(req)
			.then(function (response) {
				deferred.resolve(response.data);
			});
		
		return deferred.promise;
	}

	//Initial island load
	requestIslands(baseFilter);
});