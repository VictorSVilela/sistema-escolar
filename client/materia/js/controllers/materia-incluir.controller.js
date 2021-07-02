angular.module("TreinamentoApp").controller("MateriaIncluirController", MateriaIncluirController);

MateriaIncluirController.$inject = ['$scope', 'MateriaService', '$state'];

function MateriaIncluirController($scope, MateriaService, $state) {

    $scope.incluirMateria = incluirMateria;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.descricaoPattern = /^[a-zA-Z](\s|\S|\d){0,2499}$/;
        $scope.materia = {};
        $scope.materia.professor = {};
    }

    function incluirMateria() {
        MateriaService.incluirMateria($scope.materia).then(() => {
            $state.go('materiaListar');
        }).catch(()=>{});
    }
}