describe('app function', function() {
 
  describe('app', function() {
    var $scope;
 
    beforeEach(module('app'));
 
    beforeEach(inject(function($rootScope, $controller) {
      $scope = $rootScope.$new();
      $controller('LoginController', {$scope: $scope});
    }));
      
  });
});