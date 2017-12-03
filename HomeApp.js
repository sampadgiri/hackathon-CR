(function() {
	'use strict';
	var App = angular.module('App', []);
	App.controller('MainCtrl', function($scope, $http) {
		$http.get('OrderDtls.json')
		   .then(function(res){
			  $scope.OrderDtls = res.data;                
			});
		$http.get('CarrDtls.json')
		   .then(function(res){
			  $scope.CarrDtls = res.data; 
			});
		$scope.getStars = function(rating){
			var val = parseFloat(rating);
			var size = val/5*100;
			return size + '%';
			}		
	});
})();