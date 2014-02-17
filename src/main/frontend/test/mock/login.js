describe('loginApp function', function() {
 
  describe('loginApp', function() {
    var $scope;
 
    beforeEach(module('loginApp'));
 
    beforeEach(inject(function($rootScope, $controller) {
      $scope = $rootScope.$new();
      $controller('LoginController', {$scope: $scope});
    }));
	  
	 it('Set user name and password', function() {
        $scope.user.login = 'admin';
		$scope.user.password = 'password';
    });
      
  });
});