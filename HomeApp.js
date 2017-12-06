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
			  $scope.filteredCarrDtls=angular.copy($scope.CarrDtls);
			});
		$scope.getStars = function(rating){
			var val = parseFloat(rating);
			var size = val/5*100;
			return size + '%';
			};
			
	   $scope.checkAvailability=function(carrierName,city){
			  var notfound=true;
			  angular.forEach($scope.CarrDtls, function(obj){
     
     
                if(obj.CarrName===carrierName && obj.City===city){
                           notfound=false;
                       }
                 });
			
	          return notfound;		
			};
			
			
		$scope.onCitySelect=function(city){
			var carrDetailsForCity=[];
			if(city===""){
			$scope.filteredCarrDtls=angular.copy($scope.CarrDtls);
			}
			else{
			angular.forEach($scope.CarrDtls, function(obj){
    
                if(obj.City===city){
                       carrDetailsForCity.push(obj);   
                       }
                 });
			$scope.filteredCarrDtls=carrDetailsForCity;
			}
	         // return carrDetailsForCity;		
			};
			
			$scope.filteredCarrDtls=angular.copy($scope.CarrDtls);
	});
	App.filter('unique', function() {
		return function(input, key) {
			var unique = {};
			var uniqueList = [];
			for(var i = 0; i < input.length; i++){
				if(typeof unique[input[i][key]] == "undefined"){
					unique[input[i][key]] = "";
					uniqueList.push(input[i]);
				}
			}
			return uniqueList;
		};
	});
})();