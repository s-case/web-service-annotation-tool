'use strict';

angular
    .module('angClientApp', [
        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ui.router',
        'ui.tree'
    ])
    .run(function ($rootScope, $state, Auth) {
        $rootScope.Auth = Auth;
        console.log("load");
        $rootScope.$on("$stateChangeStart", function (event, toState, toParams, fromState, fromParams) {
            if(!Auth.authorize(toState.data.public)) {
                event.preventDefault();
                if(Auth.isLoggedIn()) {
                    console.log("Go Home");
                    $state.go('main.home');
                } else {
                    console.log("Go to Signin");
                    $state.go('public.signin');
                }
            }
        });
    })
    .config(function ($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {

        $urlRouterProvider.otherwise("/");

        $httpProvider.interceptors.push(function($q, $location) {
            return {
                'responseError': function(response) {
                    if(response.status === 401 || response.status === 403) {
                        $location.path('/signin');
                    } else if(response.status === 404) {
                        $location.path('/');
                    }
                    return $q.reject(response);
                }
            };
        });

        // Public routes
        $stateProvider
        .state('public', {
            abstract: true,
            templateUrl: "views/public.html",
            controller: function($scope, $location) {
                $scope.signin = function() {
                    $location.path("/signin");
                };
                $scope.signup = function() {
                    $location.path("/signup");
                };
            },
            data: {
                public: true
            }
        })
        .state('public.signin',{
            url: '/signin',
            templateUrl: 'views/signin.html',
            controller: 'SigninCtrl'
        })
        .state('public.signup',{
            url: '/signup',
            templateUrl: 'views/signup.html',
            controller: 'SignupCtrl'
        });

        $stateProvider
            .state('main', {
                abstract: true,
                templateUrl: 'views/main.html',
                controller: 'MainCtrl',
                data: {
                    public: false
                }
            })
            .state('main.home', {
                url: '/',
                templateUrl: 'views/home.html',
                controller: 'HomeCtrl'
            })
            .state('main.services', {
                url: '/services',
                templateUrl: 'views/services.html',
                controller: 'ServicesCtrl'
            })
            .state('main.service', {
                url: '/services/:serviceid',
                templateUrl: 'views/service.html',
                controller: 'ServiceCtrl'
            })
            .state('main.soapservice', {
                url: '/soapservices/:serviceid',
                templateUrl: 'views/soapservice.html',
                controller: 'SoapserviceCtrl'
            })
            .state('main.service.base', {
                url: '/base',
                templateUrl: 'views/base.html',
                controller: 'BaseCtrl'
            })
            .state('main.soapservice.base', {
                url: '/base',
                templateUrl: 'views/soap.html',
                controller: 'SoapCtrl'
            })
            .state('main.service.method', {
                url: '/methods/:methodid',
                templateUrl: 'views/method.html',
                controller: 'MethodCtrl'
            })
            .state('main.service.params', {
                url: '/params/:paramid',
                templateUrl: 'views/param.html',
                controller: 'ParamCtrl'
            })
            .state('main.service.resource', {
                url: '/resources/:resourceid',
                templateUrl: 'views/resource.html',
                controller: 'ResourceCtrl'
            })
            .state('main.soapservice.operation', {
                url: '/operations/:operationid',
                templateUrl: 'views/operation.html',
                controller: 'OperationCtrl'
            })
            .state('main.soapservice.message', {
                url: '/messages/:messageid',
                templateUrl: 'views/message.html',
                controller: 'MessageCtrl'
            })
            .state('main.soapservice.parameter', {
                url: '/parameters/:parameterid',
                templateUrl: 'views/parameter.html',
                controller: 'ParameterCtrl'
            })
            .state('main.add', {
                url: '/add',
                templateUrl: 'views/add.html',
                controller: 'AddCtrl'
            });

    });