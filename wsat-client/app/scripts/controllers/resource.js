'use strict';

angular.module('angClientApp')
  .controller('ResourceCtrl', function ($scope, $http, $location) {

  	  	var link = $location.search().l; 

		$http.get(link)
		.success(function(data, status, headers, config) {
			var resourceKeywords = [];
			if(data.resourceKeywords) {
				if(data.resourceKeywords instanceof Array) {
					resourceKeywords = data.resourceKeywords.join();	
				} else {
					resourceKeywords = data.resourceKeywords;
				}
	  		}
			$scope.resource = {
				resourceName : data.resourceName,
				relativeUri: data.relativeUri,
				resourceDescription: data.resourceDescription,
				resourceKeywords: resourceKeywords
			};
		});

		$scope.save = function() {
			$scope.resource.resourceKeywords = $scope.resource.resourceKeywords.split(",");
			$http.put(link, $scope.resource)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};

  });
