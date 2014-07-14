'use strict';

/**
 * @ngdoc service
 * @name angClientApp.Auth
 * @description
 * # Auth
 * Factory in the angClientApp.
 */
angular.module('angClientApp').factory('Auth', function ($http, $cookieStore) {
    var currentUser = $cookieStore.get('suser') || { username: '', accountId: '', email: ''};

    $cookieStore.remove('suser');

    function changeUser(user) {
        angular.extend(currentUser, user);
    }

    return {
        authorize: function(publicAccess) {
            if(publicAccess) return true;
            if(currentUser.accountId !== '') return true;
            return false;
        },
        isLoggedIn: function(user) {
            if(user === undefined) {
                user = currentUser;
            }
            return user.accountId !== '';
        },
        signup: function(user, success, error) {
            $http.post('http://localhost:8080/wsAnnotationTool/api/account', user).success(function(res) {
                changeUser(res);
                success();
            }).error(function(err) {
                error(err);
            });
        },
        signin: function(user, success, error) {
            var credentials = user.username.concat(':').concat(user.password);
            $http.get('http://localhost:8080/wsAnnotationTool/api/account', { headers: {'Authorization': 'Basic ' + btoa(credentials)} }).success(function(user){
                console.log("success");
                changeUser(user);
                success(user);
            }).error(function(err) {
                error(err);
            });
        },
        signout: function(success, error) {
            $http.post('/signout').success(function(){
                changeUser({
                    username: '',
                    role: ''
                });
                success();
            }).error(error);
        },
        user: currentUser
    };

  });