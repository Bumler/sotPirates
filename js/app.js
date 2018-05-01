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
	var selectedIslands = {};
	selectedIslands.islands = [];
	selectedIslands.marked = [];

	return {selectedIslands};
});

sotPirates.factory('IslandMarker', function($location, SelectedIslands){
	markedIsland = SelectedIslands.marked;

	return{
		MarkOnMap : function(island){
			SelectedIslands.marked.push(island);
			$location.path('/map');
		}
	}
})

sotPirates.controller('islandModalController', function($uibModal, $scope, island){
	$scope.island = island;
});

sotPirates.controller('galleryController', function($scope, $uibModal, SelectedIslands, IslandMarker){
	$scope.selectedIslands = SelectedIslands;
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
	$scope.selectedIslands = SelectedIslands;
});

sotPirates.controller('controlsController', function($scope, $http, $q, $location, SelectedIslands){
	$scope.selectedIslands = SelectedIslands;
	
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

		promise = filterIslands(filter);
		promise.then(function(newIslands){
			$scope.selectedIslands.islands = newIslands;
		});
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

	function loadInitialIslands(){
		filter = baseFilter;

		promise = filterIslands(filter);
		promise.then(function(newIslands){
			$scope.selectedIslands.islands = newIslands;
		});
	}

	loadInitialIslands();
});