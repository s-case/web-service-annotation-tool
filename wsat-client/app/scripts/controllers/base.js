'use strict';

angular.module('angClientApp')
  .controller('BaseCtrl', function ($scope, $http, $stateParams) {

  		var accountId = 1;

  		$http.get("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid)
	  	.success(function(data, status, headers, config) {
			$scope.base = {
				wsName : data.wsName,
				wsProvider : data.wsProvider,
				baseUri: data.baseUri,
				wsDescription: data.wsDescription,
				wsKeywowrds: data.wsKeywords
			};
		});

		$scope.save = function() {
			$http.put("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid, $scope.base)
  			.success(function(data, status, headers, config) {
  				console.log("success");
  			})
  			.error(function(data, status, headers, config) { 
  				console.log("error");
  			});
	  	};

	});
