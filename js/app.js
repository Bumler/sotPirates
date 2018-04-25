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

sotPirates.service('IslandFilterService', function($http, $q){
	requestURL = "http://localhost:9099";
	this.baseFilter = "/islands?exclusive=true&filters=";
	
	this.filterIslands = function(filter){
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
});

sotPirates.factory('SelectedIslands', function(IslandFilterService){
	var currentIslands = [];
	return {islands: currentIslands};
});

sotPirates.controller('islandModalController', function($uibModal, $scope, island){
	$scope.island = island;
});

sotPirates.controller('galleryController', function($scope, $http, $uibModal, SelectedIslands, IslandFilterService){
	$scope.selectedIslands = SelectedIslands;

	function loadInitialIslands(){
		filter = IslandFilterService.baseFilter;

		promise = IslandFilterService.filterIslands(filter);
		promise.then(function(newIslands){
			$scope.selectedIslands.islands = newIslands;
		});
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

	loadInitialIslands();
});

sotPirates.controller('mapController', function($scope){

});

sotPirates.controller('filterController', function($scope, $http, $location,IslandFilterService, SelectedIslands){
	$scope.selectedIslands = SelectedIslands;

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

	$scope.updateFilter = function() {
		filter = buildFilter();

		promise = IslandFilterService.filterIslands(filter);
		promise.then(function(newIslands){
			$scope.selectedIslands.islands = newIslands;
		});
	}

	function buildFilter(){
		filter = IslandFilterService.baseFilter;
		
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
});