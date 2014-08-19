'use strict';

angular.module('angClientApp')
  .controller('ParameterCtrl', function ($scope, $location, $http) {

		var link = $location.search().l; 

		$http.get(link)
	  	.success(function(data, status, headers, config) {
	  		var keyword = [];
			if(data.keyword) {
				if(data.keyword instanceof Array) {
					keyword = data.keyword.join();	
				} else {
					keyword = data.keyword;
				}
	  		}
			$scope.parameter = {
				name : data.name,
				type : data.type,
				// description: data.description,
				keyword: keyword
			};
		});

		$scope.save = function() {
			$scope.parameter.keyword = $scope.parameter.keyword.split(",");
			$http.put(link, $scope.parameter)
			.success(function(data, status, headers, config) {
				$scope.success = true;
			})
			.error(function(data, status, headers, config) { 
				$scope.error = true;
			});
	  	}

  });
