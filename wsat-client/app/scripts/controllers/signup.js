'use strict';

angular.module('angClientApp')
  .controller('SignupCtrl', function ($scope, $location, Auth) {
    	$scope.signup = function() {
    		if($scope.retypedPassword != $scope.user.password) {
    			$scope.msg = "Passwords do not match";
    		} else if($scope.user.password.length < 6) {
    			$scope.msg = "Passwords smaller than 6 characters";
    		} else {
    			Auth.signup($scope.user,
	            function(res) {
	                console.log("signed up");
	                $location.path('/');
	            },
	            function(err) {
	                console.log(err);
	                $scope.msg = "Invalid credentials";
	            });	
    		}
        };
  });
