'use strict';

angular.module('angClientApp')
  .controller('MessageCtrl', function ($scope, $http, $location) {
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
			$scope.message = {
				name : data.name,
				description: data.description,
				keyword: keyword
			};
		});

		$scope.save = function() {
			$scope.message.keyword = $scope.message.keyword.split(",");
			$http.put(link, $scope.message)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};
  });
