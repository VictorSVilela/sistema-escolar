angular.module("TreinamentoApp")
    .directive("campoSelectMaterias", campoSelectMaterias)

function campoSelectMaterias() {
    campoSelectMateriasController.$inject = ['$scope', 'MateriaService'];

    return {
        restrict: "E",
        templateUrl: 'directives/campo-select-materias/campo-select-materias.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            ngMultiple: "@?"
        },
        controller: campoSelectMateriasController,
    }

    function campoSelectMateriasController($scope, MateriaService) {

        _inicializar();

        ///////////////////////////////

        function _inicializar() {
            $scope.options = [];

            MateriaService.getMaterias().then(materias => {
                $scope.options = materias;
            })
        }

    }

}