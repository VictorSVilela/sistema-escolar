angular.module("TreinamentoApp").controller("AlunoIncluirController", AlunoIncluirController);

AlunoIncluirController.$inject = ['$scope', 'AlunoService', '$state'];

function AlunoIncluirController($scope, AlunoService, $state) {

    $scope.adicionarAluno = adicionarAluno;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.aluno = {};
    }

    function adicionarAluno() {
        AlunoService.adicionarAluno($scope.aluno).then(() => {
            $state.go('alunoListar');
        });
    }
}