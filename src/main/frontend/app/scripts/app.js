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
	'ngRoute',
	'LoginModule'
]);


var mainApp = angular.module('mainApp', [
	'ngRoute',
	'MainModule'
]);

mainApp.config('$mainProvider', function ($mainProvider) {
	$mainProvider
		.when('/users', {
			templateUrl: 'views/users.html',
			controller: 'MainController'
		})
		.when('/loading', {
			templateUrl: 'views/users.html',
			controller: 'MainController'
		})
		.when('/contact', {
			templateUrl: 'views/users.html',
			controller: 'MainController'
		});
});