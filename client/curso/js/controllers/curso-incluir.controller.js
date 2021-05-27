angular.module("TreinamentoApp").controller("CursoIncluirController", CursoIncluirController);

CursoIncluirController.$inject = ['$scope', 'CursoService'];

function CursoIncluirController($scope, CursoService) {

    $scope.incluirCurso = incluirCurso;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        CursoService.getCursos().then(response => {
            $scope.cursos = response.data;
        });
    }

    function incluirCurso(curso) {
        CursoService.incluirCurso(curso).then(response => {
            $scope.cursos = response.data;
            listar();
        });
    }
}