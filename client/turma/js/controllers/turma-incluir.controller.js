angular.module("TreinamentoApp").controller("TurmaIncluirController", TurmaIncluirController);

TurmaIncluirController.$inject = ['$scope', 'TurmaService', '$state'];

function TurmaIncluirController($scope, TurmaService, $state) {

    $scope.$watch('turma.curso.id', _watchTurmaCurso);

    $scope.incluirTurma = incluirTurma;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.siglaPattern = /^[A-Z]{1,5}$/;
        $scope.turma = {
            curso: {},
            alunosIds: []
        };
    }

    function incluirTurma() {
        TurmaService.incluirTurma($scope.turma).then(response => {
            $scope.turma = response;
            $state.go('turmaListar', {id: $scope.turma.id});
        });
    }

    function _watchTurmaCurso(newValue) {
        if (newValue) {
            TurmaService.gerarMatricula($scope.turma.curso.id).then(response => {
                $scope.turma.matricula = response.data;
            });
        }
    }

}