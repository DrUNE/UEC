'use strict';

/**
 * js login controller
 */
angular.module('LoginModule', ['ui.bootstrap'])
	.controller('LoginController', function ($scope, $modal, $http) {
		$scope.master = {};
		var modalRoleWindow;

		$scope.reset = function () {
			$scope.user = angular.copy($scope.master);
		};

		$scope.load_reles = function () {
			modalRoleWindow = $modal.open({
				templateUrl: 'views/role.html',
				controller: 'RoleController'
			});

			modalRoleWindow.result.then(function () {
				alert('modal');
			});
		};

	});

/* js for modal window */
var RoleController =  function ($scope, $modalInstance, $http) {

		$scope.windowClose = function () {
			$modalInstance.close();
		};

		$scope.login = function () {
			/*$http.post('/uec/login', angular.toJson($scope.user))
				.success(function (data) {

				})
				.fail(function () {

				});*/
			$modalInstance.close();
		};

	};