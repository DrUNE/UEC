'use strict';

angular.module('mainControllers', ['ngTable'])

.controller('FuoController', function ($scope, $http) {

	var fuos_test = [
		{
			name: "ФУО1",
			regionName: "",
			inn: "",
			kpp: "",
			address: "",
			category: ""
		}
	];

	$http.get('/static-web/dictionary/fuo').success(function (data) {
		$scope.fuos = data;
	})
		.error(function (data) {
			$scope.fuos = fuos_test;
		});

})

.controller('RegionController', function ($scope, $http) {

	$scope.regions = [
		{
			name: "Амурская область",
			code: "123"
		}
	];

})

.controller('StatusController', function ($scope, $http) {

	$scope.status = [
		{
			code: "234234",
			name: "",
			description: ""
		}
	];

});