angular.module("TreinamentoApp").controller("TurmaIncluirController", TurmaIncluirController);

TurmaIncluirController.$inject = ['$scope', 'TurmaService', '$state'];

function TurmaIncluirController($scope, TurmaService, $state) {

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.incluirTurma = incluirTurma;
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.siglaPattern = /^[A-Z]{1,5}$/;
        $scope.turma = {
            curso: {},
            alunosIds: []
        };
    }

    function incluirTurma() {
        TurmaService.incluirTurma($scope.turma).then( response => {
            $scope.turma = response;
            $state.go('turmaListar', {id: $scope.turma.id});
        });
    }
}