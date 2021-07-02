angular.module("TreinamentoApp").controller("EscolaEditarController", EscolaEditarController);

EscolaEditarController.$inject = ['$scope', 'EscolaService', '$stateParams', '$state'];

function EscolaEditarController($scope, EscolaService, $stateParams, $state) {

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.escola = {};
        $scope.editarEscola = editarEscola;
        _consultar();
    }

    function _consultar(){
        EscolaService.consultar($stateParams.id).then(response => {
            $scope.escola = response.data;
        }).catch(()=>{
            $state.go('escolaListar');
        });
    }

    function editarEscola() {
        EscolaService.editarEscola($scope.escola).then(() => {
            $state.go('escolaListar');
        }).catch(()=>{});
    }
}