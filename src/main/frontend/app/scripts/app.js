'use strict';


var feedbackApp = angular.module('feedbackApp', [
    'ngRoute',
    'feedbackControllers'
    ]);

feedbackApp.config(['$routeProvider',
    function ($routeProvider) {
		$routeProvider.
		when('/feedback', {
			templateUrl: 'views/feedback.html',
			controller: 'FeedbackCtrl'
		}).
		when('/feedbackSendSuccess', {
			templateUrl: 'views/feedbackSendSuccess.html',
			controller: 'FeedbackSendSuccessCtrl'
		}).
		when('/testIndex', {
			templateUrl: 'views/testIndex.html',
			controller: 'TestIndexCtrl'
		}).
		otherwise({
			redirectTo: '/testIndex'
		});
    }]);



var loginApp = angular.module('loginApp', [
	'LoginModule'
]);


var mainApp = angular.module('mainApp', [
	'ngRoute',
	'mainControllers'
]);

mainApp.config(['$routeProvider',
    function ($routeProvider) {
		$routeProvider.
		when('/users', {
			templateUrl: 'views/main/users.html',
			controller: 'MainController'
		}).
		when('/loading', {
			templateUrl: 'views/main/loading.html',
			controller: 'MainController'
		}).
		when('/exports', {
			templateUrl: 'views/main/exports.html',
			controller: 'MainController'
		}).
		when('/rejects', {
			templateUrl: 'views/main/rejects.html',
			controller: 'MainController'
		}).
		otherwise({
			redirectTo: '/users'
		});
    }]);