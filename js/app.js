var sotPirates = angular.module('sotPirates', ['ngRoute']);

sotPirates.config(['$routeProvider', function($routeProvider){
	$routeProvider
	.when('/gallery',{
		templateUrl: 'html/gallery.html',
		controller: 'mainController'
	})
	.otherwise({
		redirectTo: '/gallery'
	});

}]);

sotPirates.factory('SelectedIslands', function(){
    return [];
});

sotPirates.controller('mainController', function($scope, $http, SelectedIslands){
	$scope.selectedIslands = SelectedIslands;
});

sotPirates.controller('filterController', function($scope, $http, SelectedIslands){
	$scope.selectedIslands = SelectedIslands;
	requestURL = "http://192.168.1.116:9099";

	$scope.filters = 
		{chickens:false,
		snakes:false,
		pigs:false,
		outpost:false,
		fort:false,
		name:""};

	baseFilter = "/islands?exclusive=true&filters=";

	$scope.updateFilter = function() {
		filter = buildFilter();
		filterIslands(filter);
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

	function filterIslands (filter){
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
				updateSelectedIslands(response.data);
			})
	}

	function updateSelectedIslands (newIslands){
		$scope.selectedIslands = [];

		angular.forEach(newIslands, function(value, key) {
  			$scope.selectedIslands.push(value);
		});
	}
});