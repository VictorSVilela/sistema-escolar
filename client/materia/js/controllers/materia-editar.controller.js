angular.module("TreinamentoApp").controller("MateriaEditarController", MateriaEditarController);

MateriaEditarController.$inject = ['$scope', 'MateriaService', '$stateParams'];

function MateriaEditarController($scope, MateriaService, $stateParams) {

    $scope.editarMateria = editarMateria;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.materia = {};
        _consultar();
    }

    function _consultar(){
        MateriaService.consultar($stateParams.id).then(response => {
            $scope.materia = response.data;
        });
    }

    function editarMateria(id, materia) {
        MateriaService.editarMateria(id, materia).then(response => {
            $scope.materias = response.data;;
        });
    }
}