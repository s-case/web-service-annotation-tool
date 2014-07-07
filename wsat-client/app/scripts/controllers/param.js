'use strict';

angular.module('angClientApp')
  .controller('ParamCtrl', function ($scope, $http, $location) {

  	  	var link = $location.search().l; 

  	  	$http.get(link)
	  	.success(function(data, status, headers, config) {
			$scope.parameter = {
				parameterName : data.parameterName,
				parameterType : data.parameterType,
				parameterRequired : data.parameterRequired,
				parameterStyle : data.parameterStyle,
				parameterDescription: data.parameterDescription,
				parameterKeywords: data.parameterKeywords
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
