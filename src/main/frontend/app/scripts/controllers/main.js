'use strict';

angular.module('feedbackControllers', [])

    .controller('FeedbackCtrl', function($scope, $location){
        angular.extend($scope, {
            feedback : {
                message:'',
                name:'',
                email:'',
                captcha: null
            },

            submitPressed: false,

            isValid: function(formInput){
                return formInput.$invalid && $scope.submitPressed;
            },

            sendFeedback: function(form){
                $scope.submitPressed = true;
                if(form.$valid){
                    $location.path('/feedbackSendSuccess')
                }
            }
        });
    })

    .controller('FeedbackSendSuccessCtrl', function($scope){

    })

    .controller('TestIndexCtrl', function($scope){

    });
