angular.module("TreinamentoApp").controller("AlunoListarController", AlunoListarController);

AlunoListarController.$inject = ['$scope', 'AlunoService'];

function AlunoListarController($scope, AlunoService) {

    $scope.$watch('aluno.matricula', _watchAlunoTurma);

    $scope.deletarAluno = deletarAluno;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        AlunoService.getALunos().then(response => {
            $scope.alunos = response;
        });
    }

    function deletarAluno(id) {
        if (confirm(`Deseja deletar o aluno ${id}`)) {
            AlunoService.deletarAluno(id).then(() => {
                $scope.alunos = $scope.alunos.filter(aluno => aluno.id !== id);
            }).catch(()=>{});
        }
    }

    function _watchAlunoTurma(newValue) {
        if (newValue) {
            AlunoService.gerarMatricula($scope.aluno.matricula).then(response => {
                $scope.aluno.matricula = response.data;
            });
        }
    }

}
