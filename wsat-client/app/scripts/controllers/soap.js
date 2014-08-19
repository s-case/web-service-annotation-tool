'use strict';

angular.module('angClientApp')
  .controller('SoapCtrl', function ($scope, $http, $location) {
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
			$scope.soap = {
				name : data.name,
				description: data.description,
				keyword: keyword
			};
		});

		$scope.save = function() {
			$scope.soap.keyword = $scope.soap.keyword.split(",");
			$http.put(link, $scope.soap)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};
  });
