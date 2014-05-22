'use strict';

describe('Controller: ResourceCtrl', function () {

  // load the controller's module
  beforeEach(module('angClientApp'));

  var ResourceCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ResourceCtrl = $controller('ResourceCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
