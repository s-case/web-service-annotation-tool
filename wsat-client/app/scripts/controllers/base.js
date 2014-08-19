'use strict';

angular.module('angClientApp')
  .controller('BaseCtrl', function ($scope, $http, $location) {

  		var link = $location.search().l; 

  		$http.get(link)
	  	.success(function(data, status, headers, config) {
	  		var wsKeywords = [];
			if(data.wsKeywords) {
				if(data.wsKeywords instanceof Array) {
					wsKeywords = data.wsKeywords.join();	
				} else {
					wsKeywords = data.wsKeywords;
				}
	  		}
			$scope.base = {
				wsName : data.wsName,
				wsProvider : data.wsProvider,
				baseUri: data.baseUri,
				wsDescription: data.wsDescription,
				wsKeywords: wsKeywords
			};
		});

		$scope.save = function() {
			$scope.base.wsKeywords = $scope.base.wsKeywords.split(",");
			$http.put(link, $scope.base)
  			.success(function(data, status, headers, config) {
  				$scope.success = true;
  			})
  			.error(function(data, status, headers, config) { 
  				$scope.error = true;
  			});
	  	};

	});
