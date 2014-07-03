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
            .state('home.service.base', {
                url: '/base',
                templateUrl: 'views/base.html',
                controller: 'BaseCtrl'
            })
            .state('home.service.method', {
                url: '/resources/:resourceid/methods/:methodid',
                templateUrl: 'views/method.html',
                controller: 'MethodCtrl'
            })
            .state('home.service.resource', {
                url: '/resources/:resourceid',
                templateUrl: 'views/resource.html',
                controller: 'ResourceCtrl'
            })
            .state('home.service.resourceparams', {
                url: '/resourceparams/:resourceparamid',
                templateUrl: 'views/resourceparam.html',
                controller: 'ResourceParamCtrl'
            })
            .state('home.service.queryparams', {
                url: '/queryparams/:queryparamid',
                templateUrl: 'views/queryparam.html',
                controller: 'QueryParamCtrl'
            })
            .state('home.add', {
                url: 'add/',
                templateUrl: 'views/add.html',
                controller: 'AddCtrl'
            });

    });