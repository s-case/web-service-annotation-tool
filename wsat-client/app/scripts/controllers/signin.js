'use strict';

angular.module('angClientApp')
  .controller('SigninCtrl', function ($scope, $location, Auth) {
    	$scope.signin = function() {
            Auth.signin({
                username: $scope.username,
                password: $scope.password
            },
            function(res) {
                console.log("signed");
                $location.path('/home');
            },
            function(err) {
                console.log(err);
                $scope.msg = "Invalid credentials";
            });
        };
  });
