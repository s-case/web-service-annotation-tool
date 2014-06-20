'use strict';

angular.module('angClientApp')
    .controller('ServicesCtrl', function ($scope, $http) {
          var accountId = 2;
          $http.get("http://localhost:8080/wsAnnotationTool/api/account/" + accountId + "/RESTService")
          .success(function(data, status, headers, config) {
            $scope.services = data.linkList;
            for(var i in $scope.services) {
              $scope.services[i]["id"] = $scope.services[i].uri.charAt($scope.services[i].uri.length - 1);
            }
          });
      });
