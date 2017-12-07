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
         $window.location.href = '#!/success'
     }
})