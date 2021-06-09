angular.module("TreinamentoApp").controller("AlunoIncluirController", AlunoIncluirController);

AlunoIncluirController.$inject = ['$scope', 'AlunoService'];

function AlunoIncluirController($scope, AlunoService) {

    $scope.adicionarAluno = adicionarAluno;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        AlunoService.getALunos().then(response => {
            $scope.alunos = response.data;
        });
    }

    function adicionarAluno(aluno) {
        AlunoService.adicionarAluno(aluno).then(response => {
            $scope.alunos = response.data;
            listar();
        });
    }
}