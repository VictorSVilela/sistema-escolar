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
            $scope.cursos = response;
        });
    }

    function deletarCurso(id) {
        if (confirm(`Deseja deletar o curso: ${id}?`)) {
            CursoService.deletarCurso(id).then(response => {
                $scope.cursos = $scope.cursos.filter(curso => curso.id !== id);
            });
        }
    }

}