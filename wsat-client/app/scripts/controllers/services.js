'use strict';

angular.module('angClientApp')
	.controller('ServicesCtrl', function ($scope, $http) {
		var accountId = 1;
		$http.get("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService")
		.success(function(data, status, headers, config) {
		$scope.services = [];
			for(var i in data.linkList) {
				if(data.linkList[i].type == "Child") {
					console.log(data.linkList[i]);
					var id = data.linkList[i].uri.substring(data.linkList[i].uri.lastIndexOf("/") + 1, data.linkList[i].uri.length);
					$scope.services.push({
						"id": id,
						"rel": data.linkList[i].rel
					});
				}
            }
          });
      });
