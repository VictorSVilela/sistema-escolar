angular.module("TreinamentoApp").controller("TurmaEditarController", TurmaEditarController);

TurmaEditarController.$inject = ['$scope', 'TurmaService', '$stateParams', '$state'];

function TurmaEditarController($scope, TurmaService, $stateParams, $state) {

    $scope.editarTurma = editarTurma;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
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

    function editarTurma() {
        TurmaService.editarTurma($scope.turma).then(()=>{
            $state.go('turmaListar');
        }).catch(()=>{});
    }
}