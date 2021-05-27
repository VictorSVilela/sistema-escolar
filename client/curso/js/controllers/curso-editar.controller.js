angular.module("TreinamentoApp").controller("CursoEditarController", CursoEditarController);

CursoEditarController.$inject = ['$scope', 'CursoService', '$stateParams'];

function CursoEditarController($scope, CursoService, $stateParams) {

    $scope.editarCurso = editarCurso;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.curso = {};
        _consultar();
    }

    function _consultar(){
        CursoService.consultar($stateParams.id).then(response => {
            $scope.curso = response.data;
        });
    }

    function editarCurso(id, curso) {
        CursoService.editarCurso(id, curso).then(response => {
            $scope.cursos = response.data;;
        });
    }
}