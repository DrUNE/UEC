'use strict';

angular.module('feedbackControllers', [])

    .controller('FeedbackCtrl', function($scope, $location, $http){
        var
            postFeedback = function(){
                $http
                    .post('/rest/feedbacks', feedbackData($scope.feedback))
                    .success(onPostSuccess);
            },

            onPostSuccess = function(data){
                $location.path('/feedbackSendSuccess');
            },

            feedbackData = function(feedback) {
                return {
                    "id":null,
                    "message":feedback.message,
                    "fullName":feedback.name,
                    "email":feedback.email
                };
            };

        $scope.feedback = {
            message:'',
            name:'',
            email:'',
            captcha: null
        };

        $scope.submitPressed = false;

        $scope.isValid = function(formInput){
            return formInput.$invalid && $scope.submitPressed;
        };

        $scope.sendFeedback = function(form){
            $scope.submitPressed = true;
            if(form.$valid){
                postFeedback();
            }
        };

        $scope.captchaImgSrcTime = new Date().getTime();

        $scope.refreshCaptchaImg = function(){
            $scope.captchaImgSrcTime = new Date().getTime();
        };
    })

    .controller('FeedbackSendSuccessCtrl', function($scope){

    })

    .controller('TestIndexCtrl', function($scope){

    });
