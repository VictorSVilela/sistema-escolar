angular.module("TreinamentoApp").controller("MateriaEditarController", MateriaEditarController);

MateriaEditarController.$inject = ['$scope', '$state', 'MateriaService', '$stateParams'];

function MateriaEditarController($scope, $state, MateriaService, $stateParams) {

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
        }).catch(()=>{
            $state.go('materiaListar');
        });
    }

    function editarMateria() {
        MateriaService.editarMateria($scope.materia).then(() => {
            $state.go('materiaListar');
        }).catch(()=>{});
    }
}
