'use strict';

angular
    .module('angClientApp', [
        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ui.router',
        'ui.tree'
    ])
    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/");

        $stateProvider
            .state('main', {
                abstract: true,
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .state('main.home', {
                url: '/',
                templateUrl: 'views/home.html',
                controller: 'HomeCtrl'
            })
            .state('main.signin', {
                url: '/signin',
                templateUrl: 'views/signin.html',
                controller: 'SigninCtrl'
            })
            .state('main.signup', {
                url: '/signup',
                templateUrl: 'views/signup.html',
                controller: 'SignupCtrl'
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
            .state('main.service.queryparams', {
                url: '/queryparams/:queryparamid',
                templateUrl: 'views/queryparam.html',
                controller: 'QueryparamCtrl'
            })
            .state('main.service.method', {
                url: '/methods/:methodid',
                templateUrl: 'views/method.html',
                controller: 'MethodCtrl'
            })
            .state('main.service.params', {
                url: '/params/:paramid',
                templateUrl: 'views/resourceparam.html',
                controller: 'ResourceparamCtrl'
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