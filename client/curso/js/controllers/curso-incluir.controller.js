angular.module("TreinamentoApp").controller("CursoIncluirController", CursoIncluirController);

CursoIncluirController.$inject = ['$scope', 'CursoService', '$state'];

function CursoIncluirController($scope, CursoService, $state) {

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.incluirCurso = incluirCurso;
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.descricaoPattern = /^[a-zA-Z](\s|\S|\d){0,2499}$/;
        $scope.siglaPattern = /^[A-Z]{1,5}$/;
        $scope.curso = {};
        $scope.curso.coordenador={};
        $scope.curso.escola={};
        $scope.curso.materiasIds=[];
    }

    function incluirCurso() {
        CursoService.incluirCurso($scope.curso).then( response => {
            $scope.curso = response;
            $state.go('cursoListar', {id: $scope.curso.id});
        });
    }
}