angular.module("TreinamentoApp").controller("MateriaListarController", MateriaListarController);

MateriaListarController.$inject = ['$scope', 'MateriaService'];

function MateriaListarController($scope, MateriaService) {

    $scope.deletarMateria = deletarMateria;

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

    function deletarMateria(id) {
        MateriaService.deletarMateria(id).then(response => {
            $scope.materias = $scope.materias.filter(materia => materia.id !== id);
            $(".modal-backdrop").css("display","none");
        });
    }

}