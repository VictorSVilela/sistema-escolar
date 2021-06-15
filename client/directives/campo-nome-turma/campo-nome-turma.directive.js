angular.module("TreinamentoApp")
    .directive("campoNomeTurma", campoNomeTurma);


function campoNomeTurma() {
    return {
        templateUrl: 'directives/campo-nome-turma/campo-nome-turma.html',
        restrict: 'E',
        scope: {
            id: "@",
            label: "@",
            type: "@",
            ngModel: "=",
        }
    }
}