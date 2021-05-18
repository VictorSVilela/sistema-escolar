angular.module("TreinamentoApp").controller("AlunoIncluirController", AlunoIncluirController);

AlunoIncluirController.$inject = ['$scope', 'AlunoService'];

function AlunoIncluirController($scope, AlunoService) {

    $scope.adicionarAluno = adicionarAluno;

    ////////////////////////////////////////////////

    function adicionarAluno(aluno) {
        AlunoService.adicionarAluno(aluno).then(response => {
            $scope.alunos = response.data;
            delete $scope.aluno;
            $scope.alunoForm.$setPristine();
        });
    }
}