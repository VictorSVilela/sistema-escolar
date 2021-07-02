angular.module("TreinamentoApp")
    .directive("campoTexto", campoNome);


function campoNome() {
    return {
        templateUrl: 'directives/campo-texto/campo-texto.html',
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