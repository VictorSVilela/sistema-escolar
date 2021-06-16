angular.module("TreinamentoApp").controller("EscolaListarController", EscolaListarController);

EscolaListarController.$inject = ['$scope', 'EscolaService'];

function EscolaListarController($scope, EscolaService) {

    $scope.deletarEscola = deletarEscola;

    $scope.mudarStatus = function (escola) {
        if(escola.status) {
            return "Ativo";
        } else {
            return "Inativo"
        }
    }

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

    function deletarEscola(id) {
        if (confirm(`Deseja deletar a escola: ${id}?`)) {
            EscolaService.deletarEscola(id).then(response => {
                $scope.escolas = $scope.escolas.filter(escola => escola.id !== id);
            });
        }
    }

}