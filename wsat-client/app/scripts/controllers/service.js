'use strict';

angular.module('angClientApp')
	.controller('ServiceCtrl', function ($scope, $http, $stateParams) {

		var accountId = 1;
  	
  		var getRootNodesScope = function() {
      		return angular.element(document.getElementById("tree-root")).scope();
    	};

		$http.get("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid)
	  	.success(function(data, status, headers, config) {
			$scope.service = data;

		    $scope.list = [ {
		    	"id" : $scope.service.RESTServiceId,
		    	"type": "base",
		    	"title": "Base",
		    	"link": "/services/" + $stateParams.serviceid + "/base",
		    	"expandLink": "http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid + "/resource",
		    	"collapsed": true,
		    	"items": []
		    }];

	  	});

		$scope.play = function(scope) {
			console.log(scope);
			var nodeData = scope.$modelValue;
			console.log(nodeData);
			if(!(nodeData.items.length > 0)) {
				if(nodeData.type == "base") {
					$http.get(nodeData.expandLink)
					.success(function(data, status, headers, config) {
						var resources = data.linkList;
						for(var i in resources) {
							if(resources[i].type == "Child" && resources[i].httpVerb == "GET") {
								var id = resources[i].uri.substring(resources[i].uri.lastIndexOf("/") + 1, resources[i].uri.length);
								var obj = {
									"id" : id,
									"type": "resource",
									"title": (resources[i].rel === "null" ? "Resource " + id : resources[i].rel),
									"link": "/services/" + $stateParams.serviceid + "/resources/" + id,
									"expandLink": nodeData.expandLink + "/" + id,
									"collapsed": true,
									"items": []
								};
								nodeData.items.push(obj);
							}
						}
					});
				} else if(nodeData.type == "resource") {
					$http.get(nodeData.expandLink+"/RESTMethod")
					.success(function(data, status, headers, config) {
						var resources = data.linkList;
						for(var i in resources) {
							if(resources[i].type == "Child") {
								var id = resources[i].uri.substring(resources[i].uri.lastIndexOf("/") + 1, resources[i].uri.length);
								var obj = {
									"id" : id,
									"type": "method",
									"title": resources[i].rel,
									"link": nodeData.link + "/methods/" + id,
									"expandLink": nodeData.expandLink + "/RESTMethod/" + id + "/RESTParameter",
									"collapsed": true,
									"items": []
								};
								nodeData.items.push(obj);
							}
						}
					});
				}
			}
			nodeData.collapsed = !nodeData.collapsed
			scope.toggle();
		};

 //    $scope.list = [{
	// 	"id": 1,
	// 	"title": "Base",
	// 	"link" : "/services/1/bases/1",
	// 	"items": [{
	// 		"id": 2,
	// 		"title": "Resource 1",
	// 		"link" : "/services/1/resources/1",
	// 		"items": [{
	// 			"id": 3,
	// 			"title": "Resource Param 1",
	// 			"link" : "/services/1/resourceparams/1",
	// 			"items": []
 //        	}, {
	// 			"id": 4,
	// 			"title": "Method 1",
	// 			"link" : "/services/1/methods/1",
	// 			"items": [{
	//           		"id": 4,
 //    	      		"title": "Query Param 1",
 //    	      		"link" : "/services/1/queryparams/1",
 //        	  		"items": []
 //          		}, {
	// 				"id": 4,
	// 				"title": "Query Param 2",
	// 				"link" : "/services/1/queryparams/2",
	// 				"items": []
 //          		}]
 //        	}, {
	// 			"id": 5,
	// 			"title": "Method 2",
	// 			"link" : "/services/1/methods/2",
	// 			"items": []
	// 		}],
	// 	}, {
	// 		"id": 5,
	// 		"title": "Resource 2",
	// 		"link" : "/services/1/resources/2",
	// 		"items": [{
	// 			"id": 3,
	// 			"title": "Resource Param 2",
	// 			"link" : "/services/1/resourceparams/2",
	// 			"items": []
	// 		}, {
	// 			"id": 4,
	// 			"title": "Method 3",
	// 			"link" : "/services/1/methods/3",
	// 			"items": [{
	// 				"id": 4,
	// 				"title": "Query Param 3",
	// 				"link" : "/services/1/queryparams/3",
	// 				"items": []
	// 			}, {
	// 				"id": 4,
	// 				"title": "Query Param 4",
	// 				"link" : "/services/1/queryparams/4",
	// 				"items": []
	// 			}]
	// 		}]
	// 	}]
	// }]

  });
