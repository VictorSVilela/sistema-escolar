angular.module("TreinamentoApp").controller("EscolaEditarController", EscolaEditarController);

EscolaEditarController.$inject = ['$scope', 'EscolaService', '$stateParams'];

function EscolaEditarController($scope, EscolaService, $stateParams) {

    $scope.editarEscola = editarEscola;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.escola = {};
        _consultar();
    }

    function _consultar(){
        EscolaService.consultar($stateParams.id).then(response => {
            $scope.escola = response.data;
        }).catch(()=>{
            $state.go('escolaListar');
        });
    }

    function editarEscola(id, escola) {
        EscolaService.editarEscola(id, escola).then(() => {
            $state.go('escolaListar');
        }).catch(()=>{});
    }
}