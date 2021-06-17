angular.module("TreinamentoApp")
    .directive("campoNome", campoNome);


function campoNome() {
    return {
        templateUrl: 'directives/campo-nome/campo-nome.html',
        restrict: 'E',
        scope: {
            id: "@",
            label: "@",
            type: "@",
            ngModel: "=",
            max: "@",
            leitura: '=?',
            ngPattern: "=?",
            ngRequired: "=?",
            ngReadonly: "=?",
            ngDisabled: "=?"
        },
        link: function (scope) {
            if (scope.max === undefined) {
                scope.max = "";
            }
            if (scope.ngPattern === undefined) {
                scope.ngPattern = "";
            }
        }
    }
}