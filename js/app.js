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

sotPirates.factory('SelectedIslands', function(){
	filteredIslands = [];
	//SelectedIslands.markedIslands = [];

	return {
		FilteredIslands : filteredIslands,
		UpdateFilteredIsands : updateFilteredIslands,
		markedIslands : {}
	}

	function getFilteredIslands (){
		return filteredIslands;
	}

	function updateFilteredIslands (newIslands){
		//We can't simply set filtered islands to new islands because the reference is watched
		//this feels like a dirty fix and should be checked on
		filteredIslands.length = 0;

		newIslands.forEach(function(island){
			filteredIslands.push(island);
		});
	}
});

sotPirates.factory('IslandMarker', function($location, SelectedIslands){
	return{
		MarkOnMap : function(island, selectedIslands){
			selectedIslands.marked.push(island);
			$location.path('/map');
		}
	}
})

sotPirates.controller('islandModalController', function($uibModal, $scope, island){
	$scope.island = island;
});

sotPirates.controller('galleryController', function($scope, $uibModal, SelectedIslands, IslandMarker){
	$scope.filteredIslands = SelectedIslands.FilteredIslands;
	$scope.MarkOnMap = IslandMarker.MarkOnMap;

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

sotPirates.controller('mapController', function($scope, SelectedIslands){
	$scope.filteredIslands = SelectedIslands.FilteredIslands;
});

sotPirates.controller('controlsController', function($scope, $http, $q, $location, SelectedIslands){
	requestURL = "http://localhost:9099";
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

		addressURL = requestURL.concat(filter);

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
			SelectedIslands.UpdateFilteredIsands(newIslands);
		});
	}

	function loadInitialIslands(){
		filter = baseFilter;

		requestIslands(filter);
	}

	loadInitialIslands();
});