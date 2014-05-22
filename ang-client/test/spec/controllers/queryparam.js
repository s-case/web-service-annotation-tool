'use strict';

describe('Controller: QueryparamCtrl', function () {

  // load the controller's module
  beforeEach(module('angClientApp'));

  var QueryparamCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    QueryparamCtrl = $controller('QueryparamCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
