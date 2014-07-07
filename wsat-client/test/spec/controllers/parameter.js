'use strict';

describe('Controller: ParameterCtrl', function () {

  // load the controller's module
  beforeEach(module('angClientApp'));

  var ParameterCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ParameterCtrl = $controller('ParameterCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
