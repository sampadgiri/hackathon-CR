// (function() {
// 	'use strict';
	
	dodApp.controller('MainCtrl',['$scope','$http','$window','AppConstant', function($scope, $http, $window,AppConstant) {
	$scope.userName = JSON.parse(sessionStorage.getItem('userName'));

	$scope.currentPage = 1;
		$http.get(AppConstant.url+"getAllOrders")
		   .then(function(res){
			  $scope.OrderDtls = res.data;                
			});
		$http.get('CarrDtls.json')
		   .then(function(res){
			  $scope.CarrDtls = res.data; 
			  //$scope.filteredCarrDtls=angular.copy($scope.CarrDtls);
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

			$scope.goToInbox = function () {
        $window.location.href = '#!/login'
     }
			
			
		$scope.onCitySelect=function(city){
			var carrDetailsForCity=[];
			
			angular.forEach($scope.CarrDtls, function(obj){
    
                if(obj.City===city){
                       carrDetailsForCity.push(obj);   
                       }
                 });
			$scope.filteredCarrDtls=carrDetailsForCity;
			
	         // return carrDetailsForCity;		
			};
		$scope.changeCarrier=function(CarrVal,OrderDtl){
		$http.post('/ChangeCarrier',{OrderNo:OrderDtl.OrderNo,Carrier:CarrVal})
		   .then(function(res){
			  alert(res.data.status); 
			  //$scope.onCitySelect($scope.CarrDtls[1].City);
			 // $scope.CityVal=$scope.CarrDtls[1];
			  //$scope.filteredCarrDtls=angular.copy($scope.CarrDtls);
			});
		}	
	}]);
	dodApp.filter('unique', function() {
		return function(input, key) {
			var unique = {};
			var uniqueList = [];
			if(input != undefined) {
	for(var i = 0; i < input.length; i++){
				if(typeof unique[input[i][key]] == "undefined"){
					unique[input[i][key]] = "";
					uniqueList.push(input[i]);
				}
			}
			}
		
			return uniqueList;
		};
		
	});

	
// })();