'use strict';

angular.module('angClientApp').controller('OperationCtrl', function ($scope, $location, $http) {

	var link = $location.search().l;

	$http.get(link)
	.success(function(data) {
		var keyword = [];
		if(data.keyword) {
			if(data.keyword instanceof Array) {
				keyword = data.keyword.join();
			} else {
				keyword = data.keyword;
			}
		}
		$scope.operation = {
			name : data.name,
			description: data.description,
			keyword: keyword
		};
	});

	$scope.save = function() {
		$scope.operation.keyword = $scope.operation.keyword.split(',');
		$http.put(link, $scope.operation)
		.success(function() {
			$scope.success = true;
		})
		.error(function() {
			$scope.error = true;
		});
	};

});