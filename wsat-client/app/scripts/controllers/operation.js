'use strict';

angular.module('angClientApp')
  .controller('OperationCtrl', function ($scope, $location, $http) {

  		var link = $location.search().l; 

    	$http.get(link)
	  	.success(function(data, status, headers, config) {
			$scope.operation = {
				name : data.name,
				description: data.description,
				keywords: data.keywords
			};
		});

		$scope.save = function() {
			$http.put(link, $scope.operation)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};

  });
