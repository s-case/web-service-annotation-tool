'use strict';

angular.module('angClientApp')
  .controller('QueryparamCtrl', function ($scope, $http, $location) {

  	  	var link = $location.search().l; 

  	  	$http.get(link)
	  	.success(function(data, status, headers, config) {
			$scope.parameter = {
				name : data.name,
				type : data.type,
				description: data.description,
				keywords: data.keywords
			};
		});

		$scope.save = function() {
			$http.put(link, $scope.parameter)
				.success(function(data, status, headers, config) {
					$scope.success = true;
				})
				.error(function(data, status, headers, config) { 
					$scope.error = true;
				});
	  	};

  });
