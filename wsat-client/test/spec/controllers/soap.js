'use strict';

describe('Controller: SoapCtrl', function () {

  // load the controller's module
  beforeEach(module('angClientApp'));

  var SoapCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SoapCtrl = $controller('SoapCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
