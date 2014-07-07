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
            .state('home', {
                url: '/',
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .state('home.services', {
                url: 'services',
                templateUrl: 'views/services.html',
                controller: 'ServicesCtrl'
            })
            .state('home.service', {
                url: 'services/:serviceid',
                templateUrl: 'views/service.html',
                controller: 'ServiceCtrl'
            })
            .state('home.soapservice', {
                url: 'soapservices/:serviceid',
                templateUrl: 'views/soapservice.html',
                controller: 'SoapserviceCtrl'
            })
            .state('home.service.base', {
                url: '/base',
                templateUrl: 'views/base.html',
                controller: 'BaseCtrl'
            })
            .state('home.soapservice.base', {
                url: '/base',
                templateUrl: 'views/soap.html',
                controller: 'SoapCtrl'
            })
            .state('home.service.queryparams', {
                url: '/queryparams/:queryparamid',
                templateUrl: 'views/queryparam.html',
                controller: 'QueryparamCtrl'
            })
            .state('home.service.method', {
                url: '/methods/:methodid',
                templateUrl: 'views/method.html',
                controller: 'MethodCtrl'
            })
            .state('home.service.params', {
                url: '/params/:paramid',
                templateUrl: 'views/resourceparam.html',
                controller: 'ResourceparamCtrl'
            })
            .state('home.service.resource', {
                url: '/resources/:resourceid',
                templateUrl: 'views/resource.html',
                controller: 'ResourceCtrl'
            })
            .state('home.soapservice.operation', {
                url: '/operations/:operationid',
                templateUrl: 'views/operation.html',
                controller: 'OperationCtrl'
            })
            .state('home.soapservice.message', {
                url: '/messages/:messageid',
                templateUrl: 'views/message.html',
                controller: 'MessageCtrl'
            })
            .state('home.soapservice.parameter', {
                url: '/parameters/:parameterid',
                templateUrl: 'views/parameter.html',
                controller: 'ParameterCtrl'
            })
            .state('home.add', {
                url: 'add/',
                templateUrl: 'views/add.html',
                controller: 'AddCtrl'
            });

    });