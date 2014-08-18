'use strict';

angular.module('angClientApp')
  .controller('MainCtrl', function ($scope, $location, Auth) {

  		 $scope.signout = function() {
            Auth.signout(function() {
                $location.path('/');
            });
        };

  });
