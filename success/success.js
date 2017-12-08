dodApp.controller('successCtrl',function($scope,$http,$window){
        $scope.userName = JSON.parse(sessionStorage.getItem('userName'));
        $scope.orderId = sessionStorage.getItem('orderIdDetais');
        console.log($scope.orderId);



     $scope.goToInbox = function () {
        $window.location.href = '#!/login'
     }
             

})