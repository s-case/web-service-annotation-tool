'use strict';

describe('Controller: MethodCtrl', function () {

  // load the controller's module
  beforeEach(module('angClientApp'));

  var MethodCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MethodCtrl = $controller('MethodCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
