'use strict';

var dodApp = angular.module("myApp",["ngRoute","angularUtils.directives.dirPagination"]);

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
    .when("/admin",{
        templateUrl:"LogisticsDept.html",
        controller:"MainCtrl"
    })
    .when("/success",{
        templateUrl:"success/success.html",
        controller:"successCtrl"
    })
    .when("/failure",{
        templateUrl:"failure/failure.html",
        controller:"failureCtrl"
    })
});

dodApp.constant("AppConstant",{
url :"http://localhost:8085/"


})