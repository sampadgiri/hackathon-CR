dodApp.controller('userCtrl',function($scope,$http){

    $http.get('user/user.json').then(function (res) {
        $scope.productsToBuy = res.data;
    });
    $scope.userName = JSON.parse(sessionStorage.getItem('userDetails'));
     $scope.switchLocation = function () {
          $scope.location = $scope.selected;
     }
     $scope.submitReq = function () {
         console.log($scope.userName);
         console.log($scope.location);
     }
})