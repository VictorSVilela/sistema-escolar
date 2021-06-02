angular.module("TreinamentoApp").controller("CursoIncluirController", CursoIncluirController);

CursoIncluirController.$inject = ['$scope', 'CursoService', '$state'];

function CursoIncluirController($scope, CursoService, $state) {

    $scope.incluirCurso = incluirCurso;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.curso = {};
    }

    function incluirCurso() {
        CursoService.incluirCurso($scope.curso).then(() => {
            window.alert('Curso cadastrado com sucesso!');
            $state.go('cursoListar');
        });
    }
}