'use strict';

describe('Controller: ResourceparamCtrl', function () {

  // load the controller's module
  beforeEach(module('angClientApp'));

  var ResourceparamCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ResourceparamCtrl = $controller('ResourceparamCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
