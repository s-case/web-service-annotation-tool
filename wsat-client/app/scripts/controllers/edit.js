'use strict';

angular.module('angClientApp')
  .controller('EditCtrl', function ($scope, $http, $location, Auth, Location) {
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
			$http.put(Location.getAddressPfx() + '/wsAnnotationTool/api/account/' + accountId, $scope.user).success(function(res) {
				$location.path("/home");
            }).error(function(err) {
            	$scope.msg = "Error updating the profile";
            });
		}

  });
