angular.module("TreinamentoApp")
    .directive("campoTurmaMatricula", campoTurmaMatricula)

function campoTurmaMatricula() {
    return {
        templateUrl: 'directives/campo-turma-matricula/campo-turma-matricula.html',
        restrict: 'E',
        scope: {
            id: "@",
            label: "@",
            type: "@",
            ngModel: "=",
            max: "@",
            ngPattern: "@?",
            sigla: "=",
            turmaId: "=?"
        },
        controller: campoTurmaMatriculaController,
        link: function (scope) {
            if (scope.max === undefined) {
                scope.max = "";
            }
            if (scope.ngPattern === undefined) {
                scope.ngPattern = "";
            }
        }
    }

    campoTurmaMatriculaController.$inject = ['$scope', 'TurmaService'];

    function campoTurmaMatriculaController($scope,TurmaService){
        $scope.$watch('sigla', _watchNgModel)

        _inicializar();

        /////////////////////////////////

        function _inicializar() {
            if(!$scope.turmaId){
                $scope.turmaId="";
            }
        }

        function _watchNgModel(newValue){
            if (newValue) {
                TurmaService.gerarMatricula(newValue,$scope.turmaId)
                    .then(matricula => {
                        $scope.ngModel = matricula;
                    })
            }
        }
    }

}
