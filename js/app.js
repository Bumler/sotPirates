var sotPirates = angular.module('sotPirates', ['ngRoute']);

sotPirates.config(['$routeProvider', function($routeProvider){
	$routeProvider
	.when('/gallery',{
		templateUrl: 'html/gallery.html',
		controller: 'galleryController'
	})
	.otherwise({
		redirectTo: '/gallery'
	});

}]);

sotPirates.service('SelectIslands', function($http, $q){
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

sotPirates.factory('SelectedIslands', function(SelectIslands){
	var currentIslands = [];
	return {islands: currentIslands};
});

sotPirates.controller('galleryController', function($scope, $http, SelectedIslands, SelectIslands){
	$scope.selectedIslands = SelectedIslands;

	function loadInitialIslands(){
		filter = SelectIslands.baseFilter;

		promise = SelectIslands.filterIslands(filter);
		promise.then(function(newIslands){
			$scope.selectedIslands.islands = newIslands;
		});
	}

	loadInitialIslands();
});

sotPirates.controller('filterController', function($scope, $http, SelectIslands, SelectedIslands){
	$scope.selectedIslands = SelectedIslands;

	$scope.filters = 
		{chickens:false,
		snakes:false,
		pigs:false,
		outpost:false,
		fort:false,
		name:""};

	$scope.updateFilter = function() {
		filter = buildFilter();

		promise = SelectIslands.filterIslands(filter);
		promise.then(function(newIslands){
			$scope.selectedIslands.islands = newIslands;
		});
	}

	function buildFilter(){
		filter = SelectIslands.baseFilter;
		
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