'use strict';

/**
 * js login controller
 */
angular.module('LoginModule', ['ui.bootstrap'])
    .controller('LoginController', function ($scope, $modal, $http) {
        $scope.master = {};

        $scope.reset = function () {
            $scope.user = angular.copy($scope.master);
        };

        $scope.loadRoles = function () {
            var modalRoleWindow = $modal.open({
                templateUrl: 'views/role.html',
                controller: 'RoleController'
            });

            modalRoleWindow.result.then(function () {
                alert('modal');
            });
        };

    })

    .controller('RoleController', function ($scope, $modalInstance, $http) {
        $scope.windowClose = function () {
            $modalInstance.close();
        };

        $scope.login = function () {
            $http.post('/rest/login', angular.toJson($scope.user))
                .success(function (data) {

                })
                .error(function (data, status) {
                    alert(angular.toJson({data: data, status:status}, true))
                });
            $modalInstance.close();
        };
    });