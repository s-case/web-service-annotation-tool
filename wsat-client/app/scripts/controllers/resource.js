'use strict';

angular.module('angClientApp')
  .controller('ResourceCtrl', function ($scope, $http, $stateParams) {

  	  	var accountId = 1;

  	  	console.log("resource");

		$http.get("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid + "/resource/" + $stateParams.resourceid)
		.success(function(data, status, headers, config) {
			$scope.resource = {
				resourceName : data.resourceName,
				relativeUri: data.relativeUri,
				resourceDescription: data.resourceDescription,
				resourceKeywowrds: data.resourceKeywords
			};
		});

		$scope.save = function() {
			$http.put("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid + "/resource/" + $stateParams.resourceid, $scope.resource)
  			.success(function(data, status, headers, config) {
  				console.log("success");
  			})
  			.error(function(data, status, headers, config) { 
  				console.log("error");
  			});
	  	};

  });
