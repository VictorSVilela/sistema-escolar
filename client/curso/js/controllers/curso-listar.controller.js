angular.module("TreinamentoApp").controller("CursoListarController", CursoListarController);

CursoListarController.$inject = ['$scope', 'CursoService'];

function CursoListarController($scope, CursoService) {

    $scope.deletarCurso = deletarCurso;

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

    function deletarCurso(id) {
        CursoService.deletarCurso(id).then(response => {
            $scope.cursos = $scope.cursos.filter(curso => curso.id !== id);
            $(".modal-backdrop").css("display","none");
        });
    }

}