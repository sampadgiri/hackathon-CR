dodApp.controller('userCtrl',function($scope,$http,$window){

    $http.get('user/user.json').then(function (res) {
        $scope.productsToBuy = res.data;
    });
    $scope.userName = JSON.parse(sessionStorage.getItem('userName'));
 $scope.userPriority = JSON.parse(sessionStorage.getItem('userPriority'));

     $scope.switchLocation = function () {
          $scope.location = $scope.selected;
     }
     $scope.submitReq = function () {
         console.log($scope.userName);
         console.log($scope.location);
         console.log($scope.userPriority);
        //  var data = {};
        //  dataObj.userName = $scope.userName;
        //  dataObj.location = $scope.location;
        //  dataObj.userPriority = $scope.userPriority;
        //  var response = $http.post(url,dataObj);
        //  response.success(function (data,status,headers,config) {
        //               $window.location.href = '#!/success'

        //  });
        //  response.error(function (data,status,headers,config) {
        //               $window.location.href = '#!/failure';

        //  });
         $window.location.href = '#!/success'
        // $window.location.href = '#!/failure';
     }
})