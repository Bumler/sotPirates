var sotPirates = angular.module('sotPirates', ['ngRoute']).run(function($rootScope) {
    $rootScope.data = "";
});

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

sotPirates.controller('galleryController', function($scope, $http, $rootScope){
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

sotPirates.controller('filterController', function($scope, $http, $rootScope){

});