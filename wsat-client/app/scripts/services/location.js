'use strict';

angular.module('angClientApp').factory('Location', function ($location) {

    var addressPrefix = $location.protocol() + "://" + $location.host() + ":" + $location.port();

    // Public API here
    return {
      getAddressPfx: function () {
        return addressPrefix;
      }
    };
    
  });
