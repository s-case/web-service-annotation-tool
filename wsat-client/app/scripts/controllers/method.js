'use strict';

angular.module('angClientApp')
  .controller('MethodCtrl', function ($scope, $http, $location) {

  	  	var link = $location.search().l; 

  		$http.get(link)
	  	.success(function(data, status, headers, config) {
			var methodKeywords = [];
			if(data.methodKeywords) {
				if(data.methodKeywords instanceof Array) {
					methodKeywords = data.methodKeywords.join();	
				} else {
					methodKeywords = data.methodKeywords;
				}
	  		}
			$scope.method = {
				HTTPVerb : data.HTTPVerb,
				methodIdentifier: data.methodIdentifier,
				methodDescription: data.methodDescription,
				methodKeywords: data.methodKeywords
			};
		});

		$scope.save = function() {
			$scope.method.methodKeywords = $scope.method.methodKeywords.split(",");
			$http.put(link, $scope.method)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};
  });
