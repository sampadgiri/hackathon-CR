dodApp.controller('failureCtrl',function($scope,$http,$window){
        $scope.userName = JSON.parse(sessionStorage.getItem('userDetails'));


     $scope.goToInbox = function () {
        $window.location.href = '#!/login'
     }
     $scope.goToPlaceOrder = function () {
        $window.location.href = '#!/user'
     }
             

})