angular.module("TreinamentoApp").controller('homeController', function ($scope, $location) {
    $scope.message = 'Página Inicial';
    console.log($location.path())
});