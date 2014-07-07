'use strict';

angular.module('angClientApp')
  .controller('ResourceCtrl', function ($scope, $http, $location) {

  	  	var accountId = 1;

  	  	var link = $location.search().l; 

		$http.get(link)
		.success(function(data, status, headers, config) {
			$scope.resource = {
				resourceName : data.resourceName,
				relativeUri: data.relativeUri,
				resourceDescription: data.resourceDescription,
				resourceKeywowrds: data.resourceKeywords
			};
		});

		$scope.save = function() {
			$http.put(link, $scope.resource)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};

  });
