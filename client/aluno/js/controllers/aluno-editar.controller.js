angular.module("TreinamentoApp").controller("AlunoEditarController", AlunoEditarController);

AlunoEditarController.$inject = ['$scope', 'AlunoService', '$stateParams'];

function AlunoEditarController($scope, AlunoService, $stateParams) {

    $scope.editarAluno = editarAluno;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.aluno = {};
        _consultar();
    }

    function _consultar(){
        AlunoService.consultar($stateParams.id).then(response => {
            $scope.aluno = response.data;
        });
    }

    function editarAluno(id, aluno) {
        AlunoService.editarAluno(id, aluno).then(() => {
            $state.go('alunoListar');
        }).catch(() => {});
    }
}
