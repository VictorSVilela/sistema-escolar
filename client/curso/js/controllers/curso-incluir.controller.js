angular.module("TreinamentoApp").controller("CursoIncluirController", CursoIncluirController);

CursoIncluirController.$inject = ['$scope', 'CursoService', '$state'];

function CursoIncluirController($scope, CursoService, $state) {

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.incluirCurso = incluirCurso;
        $scope.curso = {};
        $scope.curso.coordenador={};
    }

    function incluirCurso() {
        CursoService.incluirCurso($scope.curso).then( response => {
            $scope.curso = response;
            $state.go('cursoListar', {id: $scope.curso.id});
        });
    }
}