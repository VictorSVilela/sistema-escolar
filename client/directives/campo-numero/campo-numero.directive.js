angular.module("TreinamentoApp")
    .directive("campoNumero", campoNumero);


function campoNumero() {
    return {
        templateUrl: 'directives/campo-numero/campo-numero.html',
        restrict: 'E',
        scope: {
            id: "@",
            label: "@",
            type: "@",
            ngModel: "=",
            max: "@",
            leitura: '=?',
            ngPattern: "=?"
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