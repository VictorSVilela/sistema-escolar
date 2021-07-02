angular.module("TreinamentoApp").controller("AlunoEditarController", AlunoEditarController);

AlunoEditarController.$inject = ['$scope', 'AlunoService', '$stateParams','$state'];

function AlunoEditarController($scope, AlunoService, $stateParams, $state) {

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

    function editarAluno() {
        AlunoService.editarAluno($scope.aluno).then(() => {
            $state.go('alunoListar');
        });
    }
}
