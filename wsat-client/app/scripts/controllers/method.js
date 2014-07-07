'use strict';

angular.module('angClientApp')
  .controller('MethodCtrl', function ($scope, $http, $location) {
  	  	var accountId = 1;

  	  	var link = $location.search().l; 

  		$http.get(link)
	  	.success(function(data, status, headers, config) {
			$scope.method = {
				HTTPVerb : data.HTTPVerb,
				methodIdentifier: data.methodIdentifier,
				methodDescription: data.methodDescription,
				methodKeywords: data.methodKeywords
			};
		});

		$scope.save = function() {
			$http.put(link, $scope.method)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};
  });
