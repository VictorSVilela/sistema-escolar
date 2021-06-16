angular.module("TreinamentoApp").controller("TurmaListarController", TurmaListarController);

TurmaListarController.$inject = ['$scope', 'TurmaService'];

function TurmaListarController($scope, TurmaService) {

    $scope.deletarTurma = deletarTurma;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        TurmaService.getTurmas().then(response => {
            $scope.turmas = response;
        });
    }

    function deletarTurma(id) {
        if (confirm(`Deseja deletar a turma ${id}`)) {
            TurmaService.deletarTurma(id).then(response => {
                $scope.turmas = $scope.turmas.filter(turma => turma.id !== id);
            });
        }
    }

}