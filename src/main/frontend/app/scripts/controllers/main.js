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
            },

            captchaImgSrcTime: new Date().getTime(),

            refreshCaptchaImg: function(){
                $scope.captchaImgSrcTime = new Date().getTime();
            }
        });
    })

    .controller('FeedbackSendSuccessCtrl', function($scope){

    })

    .controller('TestIndexCtrl', function($scope){

    });
