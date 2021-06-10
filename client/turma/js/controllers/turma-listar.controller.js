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
            $scope.turmas = response.data;
        });
    }

    function deletarTurma(id) {
        TurmaService.deletarTurma(id).then(response => {
            $scope.turmas = $scope.turmas.filter(turma => turma.id !== id);
            $(".modal-backdrop").css("display","none");
        });
    }

}