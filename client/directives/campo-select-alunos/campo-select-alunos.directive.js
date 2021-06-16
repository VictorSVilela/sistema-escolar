angular.module("TreinamentoApp")
    .directive("campoSelectAlunos", campoSelectAlunos)

function campoSelectAlunos() {
    return {
        restrict: "E",
        templateUrl: 'directives/campo-select-alunos/campo-select-alunos.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            ngMultiple: "@?"
        },
        controller: campoSelectAlunosController,
    }

    campoSelectAlunosController.$inject = ['$scope', 'AlunoService'];

    function campoSelectAlunosController($scope, AlunoService) {

        _inicializar();

        ///////////////////////////////

        function _inicializar() {
            $scope.options = [];

            AlunoService.getALunos().then(alunos => {
                $scope.options = alunos;
            })
        }

    }

}