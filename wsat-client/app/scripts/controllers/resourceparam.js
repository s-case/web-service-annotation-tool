'use strict';

angular.module('angClientApp')
  .controller('ResourceparamCtrl', function ($scope, $http, $location) {

  	  	var link = $location.search().l; 

  	  	$http.get(link)
	  	.success(function(data, status, headers, config) {
			$scope.parameter = {
				name : data.parameterName,
				type : data.parameterType,
				required : data.parameterRequired,
				style : data.parameterStyle,
				description: data.parameterDescription,
				keywords: data.parameterKeywords
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
