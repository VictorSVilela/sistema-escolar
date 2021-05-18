angular.module("TreinamentoApp").controller("AlunoEditarController", AlunoEditarController);

AlunoEditarController.$inject = ['$scope', 'AlunoService'];

function AlunoEditarController($scope, AlunoService) {

    $scope.editarAluno = editarAluno;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.aluno = {};
        _consultar();
    }

    function _consultar(){
        AlunoService.consultar(id).then(response => {
            $scope.aluno = response.data;
        });
    }

    function editarAluno(id, aluno) {
        AlunoService.editarAluno(aluno).then(response => {
            $scope.alunos = $scope.alunos.filter(aluno => aluno.id !== id);
        });
    }
}
