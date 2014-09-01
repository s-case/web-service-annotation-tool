'use strict';

angular.module('angClientApp').controller('SigninCtrl', function ($scope, $location, Auth) {
	$scope.signin = function() {
		Auth.signin({
			username: $scope.username,
			password: $scope.password
		},
		function() {
			$location.path('/home');
		},
		function() {
			$scope.msg = 'Invalid credentials';
		});
	};
});
