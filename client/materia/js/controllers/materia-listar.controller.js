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
            $scope.materias = response;
        });
    }

    function deletarMateria(id) {
        if (confirm(`Deseja deletar a matéria ${id}?`)) {
            MateriaService.deletarMateria(id).then(() => {
                $scope.materias = $scope.materias.filter(materia => materia.id !== id);
            });
        }
    }

}