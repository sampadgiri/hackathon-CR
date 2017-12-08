dodApp.controller('successCtrl',function($scope,$http,$window){
        $scope.userName = JSON.parse(sessionStorage.getItem('userName'));


     $scope.goToInbox = function () {
        $window.location.href = '#!/login'
     }
             

})