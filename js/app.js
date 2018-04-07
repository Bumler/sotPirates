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

sotPirates.controller('galleryController', function($scope){

});