angular.module("TreinamentoApp").controller("TurmaIncluirController", TurmaIncluirController);

TurmaIncluirController.$inject = ['$scope', 'TurmaService', '$state'];

function TurmaIncluirController($scope, TurmaService, $state) {

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.incluirTurma = incluirTurma;
        $scope.turma = {};
    }

    function incluirTurma() {
        TurmaService.incluirTurma($scope.turma).then( response => {
            $scope.turma = response;
            $state.go('turmaListar', {id: $scope.turma.id});
        });
    }
}