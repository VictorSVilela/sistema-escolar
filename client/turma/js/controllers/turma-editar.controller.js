angular.module("TreinamentoApp").controller("TurmaEditarController", TurmaEditarController);

TurmaEditarController.$inject = ['$scope', 'TurmaService', '$stateParams'];

function TurmaEditarController($scope, TurmaService, $stateParams) {


    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.editarTurma = _editarTurma;
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.siglaPattern = /^[A-Z]{1,5}$/;
        $scope.turma = {};
        _consultar();
    }

    function _consultar(){
        TurmaService.consultar($stateParams.id).then(response => {
            $scope.turma = response.data;
        });
    }

    function _editarTurma(id, turma) {
        TurmaService.editarTurma(id, turma).then(()=>{
            $state.go('turmaListar');
        });
    }
}