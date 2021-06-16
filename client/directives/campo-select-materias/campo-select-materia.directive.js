angular.module("TreinamentoApp")
    .directive("campoSelectMateria", campoSelectMateria)

function campoSelectMateria() {
    campoSelectMateriaController.$inject = ['$scope', 'MateriaService'];

    return {
        restrict: "E",
        templateUrl: 'directives/campo-select-materias/campo-select-materia.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
        },
        controller: campoSelectMateriaController,
    }

    function campoSelectMateriaController($scope, MateriaService) {

        _inicializar();

        ///////////////////////////////

        function _inicializar() {
            $scope.options = [];

            MateriaService.getMaterias().then(MateriaService => {
                $scope.options = MateriaService;
            })
        }

    }

}