dodApp.controller('successCtrl',function($scope,$http,$window){
        $scope.userName = JSON.parse(sessionStorage.getItem('userDetails'));


     $scope.goToInbox = function () {
        $window.location.href = '#!/login'
     }
             

})