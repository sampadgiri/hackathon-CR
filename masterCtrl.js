'use strict';

var dodApp = angular.module("myApp",["ngRoute"]);

dodApp.config (function($routeProvider){
    $routeProvider.when("/",{
        templateUrl: "login/login.html",
        controller: "loginCtrl"
    })
    .when("/login",{
        templateUrl:"login/login.html",
        controller:"loginCtrl"
    })
    .when("/user",{
        templateUrl:"user/user.html",
        controller:"userCtrl"
    })
    // .when("/admin",{
    //     templateUrl:"admin/LogisticsDept.html",
    //     controller:"MainCtrl"
    // })
})