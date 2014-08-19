'use strict';

angular.module('angClientApp')
  .controller('SoapserviceCtrl', function ($scope, $http, $stateParams, Location) {
    	var accountId = 1;
  	
  		var getRootNodesScope = function() {
      		return angular.element(document.getElementById("tree-root")).scope();
    	};

		$http.get(Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId + "/SOAPService/" + $stateParams.serviceid)
	  	.success(function(data, status, headers, config) {
			$scope.service = data;

		    $scope.list = [ {
		    	"id" : $scope.service.SOAPServiceId,
		    	"type": "base",
		    	"title": $scope.service.name,
		    	"link": "/soapservices/" + $stateParams.serviceid + "/base",
		    	"entity": Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId + "/SOAPService/" + $stateParams.serviceid,
		    	"expandLink": Location.getAddressPfx() + "/wsAnnotationTool/api/account/" + accountId + "/SOAPService/" + $stateParams.serviceid,
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
									var operations = data.linkList;
									for(var i in operations) {
										if(operations[i].type == "Child" && operations[i].httpVerb == "GET") {
											var id = operations[i].uri.substring(operations[i].uri.lastIndexOf("/") + 1, operations[i].uri.length);
											var obj = {
												"id" : id,
												"type": "operation",
												"title": (operations[i].rel === "null" ? "Resource " + id : operations[i].rel),
												"link": "/soapservices/" + $stateParams.serviceid + "/operations/" + id,
												"entity": operations[i].uri,
												"expandLink": operations[i].uri,
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
				} else if(nodeData.type == "operation") {
					$http.get(nodeData.expandLink + "/InputMessage")
					.success(function(data, status, headers, config) {
						var linkList = data.linkList;
						for(var i in linkList) {
							if(linkList[i].type == "Child" && linkList[i].httpVerb == "GET") {
								var id = linkList[i].uri.substring(linkList[i].uri.lastIndexOf("/") + 1, linkList[i].uri.length);
								var obj = {
									"id" : id,
									"type": "message",
									"title": linkList[i].rel,
									"link": "/soapservices/" + $stateParams.serviceid + "/messages/" + id,
									"entity": linkList[i].uri,
									"expandLink": linkList[i].uri,
									"collapsed": true,
									"items": []
								};
								nodeData.items.push(obj);
							}
						}
						$http.get(nodeData.expandLink + "/OutputMessage")
						.success(function(data, status, headers, config) {
							var linkList = data.linkList;
							for(var i in linkList) {
								if(linkList[i].type == "Child" && linkList[i].httpVerb == "GET") {
									var id = linkList[i].uri.substring(linkList[i].uri.lastIndexOf("/") + 1, linkList[i].uri.length);
									var obj = {
										"id" : id,
										"type": "message",
										"title": linkList[i].rel,
										"link": "/soapservices/" + $stateParams.serviceid + "/messages/" + id,
										"entity": linkList[i].uri,
										"expandLink": linkList[i].uri,
										"collapsed": true,
										"items": []
									};
									nodeData.items.push(obj);
								}
							}
						});
					});
				} else if(nodeData.type == "message") {
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
												"type": "parameter",
												"title": resources[i].rel,
												"link": "/soapservices/" + $stateParams.serviceid + "/parameters/" + id,
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
				} else if(nodeData.type == "parameter") {
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
												"type": "parameter",
												"title": resources[i].rel,
												"link": "/soapservices/" + $stateParams.serviceid + "/parameters/" + id,
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
				}
			}
			nodeData.collapsed = !nodeData.collapsed
			scope.toggle();
		};
  });
