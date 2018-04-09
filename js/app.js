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

sotPirates.controller('filterController', function($scope, SelectedIslands){
	$scope.selectedIslands = SelectedIslands;
	requestURL = "http://192.168.1.209:9099";

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

	}

	function buildFilter(){
		filter = baseFilter;
		
		filter = filter.concat('chickens:'+$scope.filters.chickens+',');
		filter = filter.concat('snakes:'+$scope.filters.snakes+',');
		filter = filter.concat('pigs:'+$scope.filters.pigs+',');

		filter = filter.concat('outpost:'+$scope.filters.outpost+',');
		filter = filter.concat('fort:'+$scope.filters.fort+',');

		filter = filter.concat('name:'+$scope.filters.name);

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
		//Process data here
	}
});