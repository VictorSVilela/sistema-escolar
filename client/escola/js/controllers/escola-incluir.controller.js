angular.module("TreinamentoApp").controller("EscolaIncluirController", EscolaIncluirController);

EscolaIncluirController.$inject = ['$scope', 'EscolaService', '$state'];

function EscolaIncluirController($scope, EscolaService, $state) {

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.escola ={};
        $scope.incluirEscola = incluirEscola;
    }

    function incluirEscola() {
        EscolaService.incluirEscola($scope.escola).then(() => {
            $state.go('escolaListar');
        }).catch(()=>{});
    }
}