'use strict';

angular.module('angClientApp')
  .controller('ParamCtrl', function ($scope, $http, $location) {

  	  	var link = $location.search().l; 

  	  	$http.get(link)
	  	.success(function(data, status, headers, config) {

			var parameterKeywords = [];
			if(data.parameterKeywords) {
				if(data.parameterKeywords instanceof Array) {
					parameterKeywords = data.parameterKeywords.join();	
				} else {
					parameterKeywords = data.parameterKeywords;
				}
	  		}

			$scope.parameter = {
				parameterName : data.parameterName,
				parameterType : data.parameterType,
				parameterRequired : data.parameterRequired,
				parameterStyle : data.parameterStyle,
				parameterDefault : data.parameterDefault,
				parameterDescription: data.parameterDescription,
				parameterValueOption: data.parameterValueOption,
				parameterKeywords: data.parameterKeywords
			};
		});

		$scope.save = function() {
			$scope.parameter.parameterKeywords = $scope.parameter.parameterKeywords.split(",");
			$http.put(link, $scope.parameter)
				.success(function(data, status, headers, config) {
					$scope.success = true;
				})
				.error(function(data, status, headers, config) { 
					$scope.error = true;
				});
	  	};

  });
