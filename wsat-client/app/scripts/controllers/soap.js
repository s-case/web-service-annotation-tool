'use strict';

angular.module('angClientApp')
  .controller('SoapCtrl', function ($scope, $http, $location) {
    	var link = $location.search().l; 

    	$http.get(link)
	  	.success(function(data, status, headers, config) {
			$scope.soap = {
				name : data.name,
				description: data.description,
				keywords: data.keywords
			};
		});

		$scope.save = function() {
			$http.put(link, $scope.soap)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};
  });
