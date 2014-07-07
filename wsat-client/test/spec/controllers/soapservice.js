'use strict';

describe('Controller: SoapserviceCtrl', function () {

  // load the controller's module
  beforeEach(module('angClientApp'));

  var SoapserviceCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SoapserviceCtrl = $controller('SoapserviceCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
