angular.module("TreinamentoApp")
    .directive("campoSelectEscola", campoSelectEscola)

function campoSelectEscola() {
    campoSelectEscolaController.$inject = ['$scope', 'EscolaService']

    return {
        restrict: "E",
        templateUrl: 'directives/campo-select/campo-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
        },
        controller: campoSelectEscolaController
    }

    function campoSelectEscolaController($scope, EscolaService) {
        _inicializar();

        function _inicializar() {
            $scope.defaultOptionText = 'Selecione...'

            $scope.options = [];

            EscolaService.getEscolas().then(escolas => {
                $scope.options = escolas;
            })
        }
    }
}