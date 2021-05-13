angular.module("TreinamentoApp").controller("helloController", HelloController);

HelloController.$inject = ['$scope', '$http'];

function HelloController($scope, $http){
    $scope.app = "Hello World - Frontend";

    $http.get('http://localhost:8080/rest/hello').then(response => {
        $scope.appBack = response.data;
    });
}
