angular.module("TreinamentoApp")
    .directive("campoSelectCurso", campoSelectCurso)

function campoSelectCurso() {
    return {
        restrict: "E",
        templateUrl: 'directives/campo-select/campo-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            ngMultiple: "@?",
            sigla: "="
        },
        controller: campoSelectCursoController
    }

    campoSelectCursoController.$inject = ['$scope', 'CursoService']

    function campoSelectCursoController($scope, CursoService) {

        _inicializar();

        function _inicializar() {
            $scope.defaultOptionText = 'Selecione...';
            $scope.options = [];
            CursoService.getCursos().then(cursos => {
                $scope.options = cursos;
            })
        }
    }
}