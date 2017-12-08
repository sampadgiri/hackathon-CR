dodApp.controller('loginCtrl', function ($scope, $http, $window) {
    $http.get('login/login.json').then(function (res) {
        $scope.loginDetails = res.data;
    })
    $scope.validate = function (username, password) {
        var isValidated = false;
        angular.forEach($scope.loginDetails, function (eachOption, eachValue) {
            var userDetails={};
            if (eachOption.userName == username && eachOption.password == password) {
                $('.errorBlock').addClass('hide');

                isValidated = true;

                if (eachOption.role == 'admin') {
                     sessionStorage.setItem('userName',JSON.stringify(username));
                     $window.location.href = '#!/admin'
                } else {
                    sessionStorage.setItem('userName',JSON.stringify(username));
                    sessionStorage.setItem('userPriority',JSON.stringify(eachOption.priority));

                    if (eachOption.priority == 'prime') {
                        $window.location.href = '#!/user'


                    } else {
                        $window.location.href = '#!/user'


                    }
                }
            }
            if (isValidated == false) {
                $window.location.href = '#!/login';
                $('.errorBlock').removeClass('hide');


            }
        })
    }
});

