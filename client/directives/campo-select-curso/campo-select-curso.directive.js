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

        $scope.$watch('ngModel', _watchNgModel);

        _inicializar();

        function _inicializar() {
            $scope.defaultOptionText = 'Selecione...'

            $scope.options = [];

            CursoService.getCursos().then(cursos => {
                $scope.options = cursos;
            })
        }

        function _watchNgModel(newValue) {
            if (newValue && $scope.options.length>0) {
                $scope.sigla = $scope.options.find(option => option.id === $scope.ngModel).sigla;
            }
        }


    }

}