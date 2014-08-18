'use strict';

angular.module('angClientApp')
  .controller('HomeCtrl', function ($scope, $http) {

  	$scope.search = function() {	
  		$http.get("http://localhost:8080/wsAnnotationTool/api/algoSearch?searchRESTResource=" + !!$scope.searchRESTResource + "&searchRESTService=" + !!$scope.searchRESTService + "&searchRESTMethod=" + !!$scope.searchRESTMethod + "&searchRESTParameter=" + !!$scope.searchRESTParameter + "&searchSOAPService=" + !!$scope.searchSOAPService + "&searchSOAPOperation=" + !!$scope.searchSOAPOperation + "&searchSOAPParameter=" + !!$scope.searchSOAPParameter + "&searchKeyword="  + $scope.searchBox)
	  	.success(function(data, status, headers, config) {
	  		if(angular.isArray(data.linkList)) {
	  			$scope.results = data.linkList;	
	  		} else {
	  			var list = [];
	  			if(data.linkList) {
	  				list.push(data.linkList)	
	  			}
	  			$scope.results = list;	
	  		}
	  		console.log($scope.results);
	  	});
  	}

  });
