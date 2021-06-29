angular.module("TreinamentoApp").controller("EscolaIncluirController", EscolaIncluirController);

EscolaIncluirController.$inject = ['$scope', 'EscolaService'];

function EscolaIncluirController($scope, EscolaService) {

    $scope.incluirEscola = incluirEscola;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        EscolaService.getEscolas().then(response => {
            $scope.escolas = response.data;
        });
    }

    function incluirEscola(escola) {
        EscolaService.incluirEscola(escola).then(response => {
            $scope.escolas = response.data;
            listar();
        }).catch(()=>{});
    }
}