'use strict';

angular.module('angClientApp')
  .controller('HomeCtrl', function ($scope, $http, Location) {

  	function createLink(uri) {
		var restIndex = uri.indexOf('RESTService/');
		if(restIndex > 0) {
			var myRe = /RESTService\/(\d)+/g;
			var myArray = myRe.exec(uri);
			var myRe2 = /RESTService\//g;
			var myArray2 = myRe2.exec(uri);
			return "services/" + uri.substring(myRe2.lastIndex, myRe.lastIndex);	
		} else {
			var myRe = /SOAPService\/(\d)+/g;
			var myArray = myRe.exec(uri);
			var myRe2 = /SOAPService\//g;
			var myArray2 = myRe2.exec(uri);
			return "soapservices/" + uri.substring(myRe2.lastIndex, myRe.lastIndex);
		}
  	}

  	$scope.search = function() {	
  		$http.get(Location.getAddressPfx() + "/wsAnnotationTool/api/algoSearch?searchRESTResource=" + !!$scope.searchRESTResource + "&searchRESTService=" + !!$scope.searchRESTService + "&searchRESTMethod=" + !!$scope.searchRESTMethod + "&searchRESTParameter=" + !!$scope.searchRESTParameter + "&searchSOAPService=" + !!$scope.searchSOAPService + "&searchSOAPOperation=" + !!$scope.searchSOAPOperation + "&searchSOAPParameter=" + !!$scope.searchSOAPParameter + "&searchKeyword="  + $scope.searchBox)
	  	.success(function(data, status, headers, config) {
	  		$scope.results = [];
	  		if(angular.isArray(data.linkList)) {
	  			for(var i in data.linkList) {
	  				var link = data.linkList[i];
	  				link.link = createLink(link.uri);
	  				$scope.results.push(link);
	  			}
	  		} else {
	  			if(data.linkList) {
	  				var link = data.linkList;
	  				link.link = createLink(link.uri);
	  				$scope.results.push(link);
	  			}
	  		}
	  	});
  	}

  });
