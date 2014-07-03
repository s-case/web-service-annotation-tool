'use strict';

angular.module('angClientApp')
  .controller('MethodCtrl', function ($scope, $http, $stateParams) {
  	  	var accountId = 1;

  	  	console.log("method");

  		$http.get("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid + "/resource/" + $stateParams.resourceid + "/RESTMethod/" + $stateParams.methodid)
	  	.success(function(data, status, headers, config) {
			$scope.method = {
				HTTPVerb : data.HTTPVerb,
				methodIdentifier: data.methodIdentifier,
				methodDescription: data.methodDescription,
				methodKeywords: data.methodKeywords
			};
		});

		$scope.save = function() {
			$http.put("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid + "/resource/" + $stateParams.resourceid + "/RESTMethod/" + $stateParams.methodid, $scope.method)
  			.success(function(data, status, headers, config) {
  				console.log("success");
  			})
  			.error(function(data, status, headers, config) { 
  				console.log("error");
  			});
	  	};
  });
