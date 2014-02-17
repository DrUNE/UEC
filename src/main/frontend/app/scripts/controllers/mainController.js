'use strict';

angular.module('mainControllers', [])
	.controller('MainController', function ($scope, $location, $http) {


	})

.controller('UserController', function ($scope, $http) {

	$scope.all_columns = [{
		"title": "#",
		"type": "string",
		"checked": true
  }, {
		"title": "Логин",
		"type": "number",
		"checked": true
  }, {
		"title": "Роли",
		"type": "string",
		"checked": true

  }, {
		"title": "Активен",
		"type": "boolean",
		"checked": false
  }, {
		"title": "Действия",
		"type": "string",
		"checked": false
  }];

	$scope.data = [{
		"#": "1",
		"Логин": "Админ",
		"Роли": "Portland",
		"Активен": 1,
		"Действия": "developer"
  }];

});