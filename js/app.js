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
	.when('/about', {
		templateUrl: 'html/about.html',
		controller: 'aboutController'
	})
	.otherwise({
		redirectTo: '/gallery'
	});

}]);

sotPirates.factory('sotEndpoints', function(){
	return{
		baseURL : "http://sotPirates.com:9099",
		setMapView : SetIslandImages,
		GET : formatGetRequest
	}

	function SetIslandImages (island){
		var isMobile = determineMobile();

		island.mapView = this.baseURL.concat("/islands/images/").concat(island.NAME).concat("?isMap=true&isMobile=" + isMobile);
		island.vanityShot = this.baseURL.concat("/islands/images/").concat(island.NAME).concat("?isMobile=" + isMobile);
	}

	function determineMobile (){
		if (window.innerWidth < 450)
			return true;
		else
			return false;
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

sotPirates.factory('IslandFactory', function(sotEndpoints){
	islands = {};
	this.showFlagged = false;

	return {
		Islands : islands,
		ShowFlagged : this.showFlagged,
		UpdateFilteredIslands : updateFilteredIslands,
		FlagIsland : flagIsland
	}

	function loadInitial (newIslands){
		for (islandName in newIslands){
			island = newIslands[islandName];

			sotEndpoints.setMapView(island);
			island.showing = true;
			island.flagged = false;

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

	function flagIsland (island, event){
		islands[island.NAME].flagged = !islands[island.NAME].flagged;
	}
});

sotPirates.controller('islandModalController', function($uibModal, $scope, island, IslandFactory){
	$scope.island = island;

	$scope.FlagIsland = IslandFactory.FlagIsland;

	$scope.close = function(){
		this.$close();
	}
});

sotPirates.controller('galleryController', function($scope, $uibModal, IslandFactory){
	$scope.Islands = IslandFactory.Islands;
	$scope.FlagIsland = IslandFactory.FlagIsland;

	$scope.hideIsland = function(island){
		if (IslandFactory.ShowFlagged)
			if (island.showing && island.flagged)
				return false;
			else
				return true;
		else
			if (island.showing)
				return false;
			else
				return true;
	}

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
	$scope.FlagIsland = IslandFactory.FlagIsland;
});

sotPirates.controller('footerController', function($scope, $location, $window){
	$scope.goToAbout = function(){
		$location.path('/about');	
		$window.scrollTo(0, 0);
	}
});

sotPirates.controller('controlsController', function($scope, $http, $q, $location, $window, sotEndpoints, IslandFactory){
	baseFilter = "/islands?";

	$scope.name = "";
	$scope.isExclusive = true;
	$scope.specialFilter = {};
	$scope.specialFilter.showFlagged = IslandFactory.ShowFlagged;

	$scope.filters = 
		{chickens:false,
		snakes:false,
		pigs:false,
		outpost:false,
		fort:false,
		docks: false,
		single: false,
		multiple: false,
		flagged: false};

	$scope.OnMapPage = function(){
		if($location.path() === "/map")
			return true;
		else
			return false;	
	}

	$scope.changeView = function(){
		if ($location.path() === '/gallery'){
			$location.path('/map');
			$window.scrollTo(0, 0);
		}
		else
			$location.path('/gallery');
	}

	$scope.updateFilter = function() {
		IslandFactory.ShowFlagged = $scope.specialFilter.showFlagged;

		filter = baseFilter;

		filter = filter.concat(NameParam()).concat("&");
		filter = filter.concat(FilterParam()).concat("&");
		filter = filter.concat(ExclusiveParam());

		requestIslands(filter, $scope.filters.flagged);
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

sotPirates.controller('aboutController', function(){
});

sotPirates.controller('headerController', function($scope, $location){
	$scope.refresh = function(){
		$location.path('/gallery');	
		location.reload();
	}
});