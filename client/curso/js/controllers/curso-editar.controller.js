angular.module("TreinamentoApp").controller("CursoEditarController", CursoEditarController);

CursoEditarController.$inject = ['$scope', 'CursoService', '$stateParams'];

function CursoEditarController($scope, CursoService, $stateParams) {


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

    function _editarCurso(id, curso) {
        CursoService.editarCurso(id, curso).then(() => {
            $state.go('cursoListar');
        });
    }
}