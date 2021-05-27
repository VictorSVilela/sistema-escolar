angular.module("TreinamentoApp").controller("MateriaIncluirController", MateriaIncluirController);

MateriaIncluirController.$inject = ['$scope', 'MateriaService'];

function MateriaIncluirController($scope, MateriaService) {

    $scope.incluirMateria = incluirMateria;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        MateriaService.getMaterias().then(response => {
            $scope.materias = response.data;
        });
    }

    function incluirMateria(materia) {
        MateriaService.incluirMateria(materia).then(response => {
            $scope.materias = response.data;
            listar();
        });
    }
}