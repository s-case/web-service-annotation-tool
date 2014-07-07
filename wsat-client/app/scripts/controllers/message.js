'use strict';

angular.module('angClientApp')
  .controller('MessageCtrl', function ($scope, $http, $location) {
		var link = $location.search().l; 

    	$http.get(link)
	  	.success(function(data, status, headers, config) {
			$scope.message = {
				name : data.name,
				description: data.description,
				keywords: data.keywords
			};
		});

		$scope.save = function() {
			$http.put(link, $scope.message)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};
  });
