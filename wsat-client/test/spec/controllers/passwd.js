'use strict';

describe('Controller: PasswdCtrl', function () {

  // load the controller's module
  beforeEach(module('angClientApp'));

  var PasswdCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PasswdCtrl = $controller('PasswdCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
