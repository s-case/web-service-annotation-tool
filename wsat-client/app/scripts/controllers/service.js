'use strict';

angular.module('angClientApp')
	.controller('ServiceCtrl', function ($scope, $http, $stateParams, Location) {

		var accountId = 1;
  	
  		var getRootNodesScope = function() {
      		return angular.element(document.getElementById("tree-root")).scope();
    	};

		$http.get(Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid)
	  	.success(function(data, status, headers, config) {
			$scope.service = data;

		    $scope.list = [ {
		    	"id" : $scope.service.RESTServiceId,
		    	"type": "base",
		    	"title": "Base",
		    	"link": "/services/" + $stateParams.serviceid + "/base",
		    	"entity": Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid,
		    	"expandLink": Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId + "/RESTService/" + $stateParams.serviceid,
		    	"collapsed": true,
		    	"items": []
		    }];

	  	});

		$scope.goto = function(item) {	  	
			console.log(item);
		}

		$scope.play = function(scope) {
			console.log(scope);
			var nodeData = scope.$modelValue;
			console.log(nodeData);
			if(!(nodeData.items.length > 0)) {
				if(nodeData.type == "base") {
					$http.get(nodeData.expandLink)
					.success(function(data, status, headers, config) {
						var linkList = data.linkList;
						for(var i in linkList) {
							if(linkList[i].type == "Child" && linkList[i].httpVerb == "GET") {
								$http.get(linkList[i].uri)
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
												"entity": resources[i].uri,
												"expandLink": resources[i].uri,
												"collapsed": true,
												"items": []
											};
											nodeData.items.push(obj);
										}
									}
								});
								break;
							}
						}
					});
				} else if(nodeData.type == "resource") {
					$http.get(nodeData.expandLink)
					.success(function(data, status, headers, config) {
						var linkList = data.linkList;
						for(var i in linkList) {
							if(linkList[i].type == "Child" && linkList[i].httpVerb == "GET" && linkList[i].rel == "RESTMethod") {
								$http.get(linkList[i].uri)
								.success(function(data, status, headers, config) {
									var resources = data.linkList;
									for(var i in resources) {
										if(resources[i].type == "Child" && resources[i].httpVerb == "GET") {
											var id = resources[i].uri.substring(resources[i].uri.lastIndexOf("/") + 1, resources[i].uri.length);
											var obj = {
												"id" : id,
												"type": "method",
												"title": resources[i].rel,
												"link": "/services/" + $stateParams.serviceid + "/methods/" + id,
												"entity": resources[i].uri,
												"expandLink": resources[i].uri,
												"collapsed": true,
												"items": []
											};
											nodeData.items.push(obj);
										}
									}
									for(var i in linkList) {
										if(linkList[i].type == "Child" && linkList[i].httpVerb == "GET" && linkList[i].rel == "RESTParameter") {
											$http.get(linkList[i].uri)
											.success(function(data, status, headers, config) {
												var resources = data.linkList;
												for(var i in resources) {
													if(resources[i].type == "Child" && resources[i].httpVerb == "GET") {
														var id = resources[i].uri.substring(resources[i].uri.lastIndexOf("/") + 1, resources[i].uri.length);
														var obj = {
															"id" : id,
															"type": "param",
															"title": resources[i].rel,
															"link": "/services/" + $stateParams.serviceid + "/params/" + id,
															"entity": resources[i].uri,
															"expandLink": resources[i].uri,
															"collapsed": false,
															"items": []
														};
														nodeData.items.push(obj);
													}
												}
											});
											break;
										}
									}
								});
								break;
							}
						}
					});
				} else if(nodeData.type == "method") {
					$http.get(nodeData.expandLink)
					.success(function(data, status, headers, config) {
						var linkList = data.linkList;
						for(var i in linkList) {
							if(linkList[i].type == "Child" && linkList[i].httpVerb == "GET") {
								$http.get(linkList[i].uri)
								.success(function(data, status, headers, config) {
									var resources = data.linkList;
									for(var i in resources) {
										if(resources[i].type == "Child" && resources[i].httpVerb == "GET") {
											var id = resources[i].uri.substring(resources[i].uri.lastIndexOf("/") + 1, resources[i].uri.length);
											var obj = {
												"id" : id,
												"type": "param",
												"title": resources[i].rel,
												"link": "/services/" + $stateParams.serviceid + "/params/" + id,
												"entity": resources[i].uri,
												"expandLink": resources[i].uri,
												"collapsed": false,
												"items": []
											};
											nodeData.items.push(obj);
										}
									}
								});
								break;
							}
						}
					});
				}
			}
			nodeData.collapsed = !nodeData.collapsed
			scope.toggle();
		};

  });
