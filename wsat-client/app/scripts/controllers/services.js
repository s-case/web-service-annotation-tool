'use strict';

angular.module('angClientApp')
	.controller('ServicesCtrl', function ($scope, $http, $location, Auth, Location) {
		var accountId = Auth.user.accountId;
		$http.get(Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId + "/RESTService")
		.success(function(data, status, headers, config) {
		$scope.services = [];
			for(var i in data.linkList) {
				if(data.linkList[i].type == "Child") {
					console.log(data.linkList[i]);
					var id = data.linkList[i].uri.substring(data.linkList[i].uri.lastIndexOf("/") + 1, data.linkList[i].uri.length);
					$scope.services.push({
						"id": id,
						"rel": data.linkList[i].rel,
						"type": "REST",
						"uri": data.linkList[i].uri
					});
				}
            }
            $http.get(Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId + "/SOAPService")
			.success(function(data, status, headers, config) {
				for(var i in data.linkList) {
					if(data.linkList[i].type == "Child") {
						console.log(data.linkList[i]);
						var id = data.linkList[i].uri.substring(data.linkList[i].uri.lastIndexOf("/") + 1, data.linkList[i].uri.length);
						$scope.services.push({
							"id": id,
							"rel": data.linkList[i].rel,
							"type": "SOAP",
							"uri": data.linkList[i].uri
						});
					}
	            }
	        });
        });


		$scope.delete = function(link, index) {
			console.log(link);
			$http.delete(link).success(function(data, status, headers, config) {
				$scope.services.splice(index, 1);
			}); 
		}
	});
