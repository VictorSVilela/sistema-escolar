angular.module("TreinamentoApp").controller("CursoEditarController", CursoEditarController);

CursoEditarController.$inject = ['$scope', 'CursoService', '$stateParams', '$state'];

function CursoEditarController($scope, CursoService, $stateParams, $state) {


    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.editarCurso = _editarCurso;
        $scope.curso = {};
        _consultar();
    }

    function _consultar(){
        CursoService.consultar($stateParams.id).then(response => {
            $scope.curso = response.data;
        });
    }

    function _editarCurso() {
        CursoService.editarCurso($scope.curso).then(() => {
            $state.go('cursoListar');
        });
    }
}