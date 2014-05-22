'use strict';

angular.module('angClientApp')
  .controller('ServiceCtrl', function ($scope) {

    $scope.list = [{
		"id": 1,
		"title": "Base",
		"link" : "/services/1/bases/1",
		"items": [{
			"id": 2,
			"title": "Resource 1",
			"link" : "/services/1/resources/1",
			"items": [{
				"id": 3,
				"title": "Resource Param 1",
				"link" : "/services/1/resourceparams/1",
				"items": []
        	}, {
				"id": 4,
				"title": "Method 1",
				"link" : "/services/1/methods/1",
				"items": [{
	          		"id": 4,
    	      		"title": "Query Param 1",
    	      		"link" : "/services/1/queryparams/1",
        	  		"items": []
          		}, {
					"id": 4,
					"title": "Query Param 2",
					"link" : "/services/1/queryparams/2",
					"items": []
          		}]
        	}, {
				"id": 5,
				"title": "Method 2",
				"link" : "/services/1/methods/2",
				"items": []
			}],
		}, {
			"id": 5,
			"title": "Resource 2",
			"link" : "/services/1/resources/2",
			"items": [{
				"id": 3,
				"title": "Resource Param 2",
				"link" : "/services/1/resourceparams/2",
				"items": []
			}, {
				"id": 4,
				"title": "Method 3",
				"link" : "/services/1/methods/3",
				"items": [{
					"id": 4,
					"title": "Query Param 3",
					"link" : "/services/1/queryparams/3",
					"items": []
				}, {
					"id": 4,
					"title": "Query Param 4",
					"link" : "/services/1/queryparams/4",
					"items": []
				}]
			}]
		}]
	}]

  });
