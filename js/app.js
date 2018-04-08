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
    return [
		{name: 'Sanctuary Outpost'},
		{name: 'Snake Island'},
		{name: 'Pig Island'}
	];
});

sotPirates.controller('mainController', function($scope, $http, SelectedIslands){
	$scope.selectedIslands = SelectedIslands;
	requestURL = "http://192.168.1.209:9099/islands";

	$scope.sendRequest = function(){
		var req = {
			method: 'GET',
 			url: requestURL,
 			headers: {
   				'Content-Type': 'application/json'
 			},
			 body: {
				 'payload' : 'Henry is cool'
			 }
 		}
		
		$http(req)
			.then(function (response) {
				viewData(response.data);
			})
	}

	function viewData (data){
		$scope.data = data;
	}
});

sotPirates.controller('filterController', function($scope, SelectedIslands){
	$scope.selectedIslands = SelectedIslands;

	$scope.updateData = function() {
		$scope.selectedIslands.push({name:'New Island'});
	}
});