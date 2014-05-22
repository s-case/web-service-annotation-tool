'use strict';

angular.module('angClientApp')
  .controller('ServicesCtrl', function ($scope) {
    $scope.services = [
    	{
    		id: 1,
    		name: "Web Service 1"
    	},
    	{
    		id: 2,
    		name: "Web Service 2"
    	},
    	{
    		id: 3,
    		name: "Web Service 3"
    	}
    ];
  });
