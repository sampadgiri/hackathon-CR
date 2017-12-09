    	dodApp.controller('userCtrl',['$scope','$http','$window','AppConstant', function($scope, $http, $window,AppConstant) {


    $http.get('user/user.json').then(function (res) {
        $scope.productsToBuy = res.data;
    });
    $http.get(AppConstant.url+"getAllCities")
	   .then(function(res){
		  $scope.cities = res.data;  
		 // $scope.selected =   $scope.cities[0];
		});
    $scope.userName = JSON.parse(sessionStorage.getItem('userName'));
 $scope.userPriority = JSON.parse(sessionStorage.getItem('userPriority'));

     $scope.switchLocation = function () {
          $scope.location = $scope.selected;
     }
   
     $scope.submitReq = function () {
        //  console.log($scope.userName);
        //  console.log($scope.location);
        //  console.log($scope.userPriority);
         var dataObj = {};
         dataObj.CustName = $scope.userName;
         dataObj.City = $scope.location;
         dataObj.CustType = $scope.userPriority;
        //  var response = $http.post(AppConstant.url+"/placeOrder",JSON.stringify(dataObj));
        //  response.success(function (data,status,headers,config) {
        //               $window.location.href = '#!/success'

        //  });
        //  response.error(function (data,status,headers,config) {
        //               $window.location.href = '#!/failure';

        //  });

         $http.post(AppConstant.url+"placeOrder",JSON.stringify(dataObj))
		   .then(function(res){
               sessionStorage.setItem('orderIdDetais',res.data.orderid);
			  $window.location.href = '#!/success'               
			},function(res){
			  $window.location.href = '#!/failure'               
			});

        //  $window.location.href = '#!/success'
        // $window.location.href = '#!/failure';
     }
}])