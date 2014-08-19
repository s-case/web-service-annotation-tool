'use strict';

angular.module('angClientApp')
  .controller('PasswdCtrl', function ($scope, $http, $location, Auth, Location) {

	var accountId = Auth.user.accountId;
		
	$http.get(Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId)
	.success(function(data, status, headers, config) {
		$scope.user = {
			accountId: data.accountId,
			email: data.email,
			password: data.password,
			username: data.username
		}

	});
    
	$scope.update = function() {
		if($scope.retypedPassword != $scope.user.password) {
			$scope.msg = "Passwords do not match";
		} else if($scope.user.password.length < 6) {
			$scope.msg = "Passwords smaller than 6 characters";
		} else {	
			$http.put(Location.getAddressPfx() + '/wsAnnotationTool/api/account/' + accountId, $scope.user).success(function(res) {
				$location.path("/home");
	        }).error(function(err) {
	        	$scope.msg = "Error updating the password";
	        });
	    }
	}

  });
