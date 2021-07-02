angular.module("TreinamentoApp").controller("AlunoIncluirController", AlunoIncluirController);

AlunoIncluirController.$inject = ['$scope', 'AlunoService', '$state'];

function AlunoIncluirController($scope, AlunoService, $state) {

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.adicionarAluno = adicionarAluno;
        $scope.aluno = {};
    }

    function adicionarAluno() {
        AlunoService.adicionarAluno($scope.aluno).then(() => {
            $state.go('alunoListar');
        }).catch(()=>{});
    }
}