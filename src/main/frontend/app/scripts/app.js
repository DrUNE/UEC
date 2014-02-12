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
