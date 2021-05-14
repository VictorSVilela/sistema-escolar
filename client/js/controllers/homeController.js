angular.module("TreinamentoApp").controller('homeController', function ($scope, $location) {
    $scope.message = 'Sistema destinado ao treinamento em Java EE';
    console.log($location.path())
});