'use strict';

angular.module('mainControllers', ['ngTable'])

.controller('FuoController', function ($scope, $http) {

	var fuos_test = [{}];

	$http.get('/uec-web/dictionary/fuo').success(function (data) {
		$scope.fuos = data;
	})
		.error(function (data) {
			$scope.fuos = fuos_test;
		});

})

.controller('RegionController', function ($scope, $http) {

	$scope.regions_test = [{}];
	
	$http.get('/uec-web/dictionary/region').success(function (data) {
		$scope.regions = data;
	})
		.error(function (data) {
			$scope.regions = regions_test;
		});

})

.controller('StatusController', function ($scope, $http) {

	$scope.status_test = [{}];
	
	$http.get('/uec-web/dictionary/status').success(function (data) {
		$scope.statuses = data;
	})
		.error(function (data) {
			$scope.statuses = status_test;
		});

});