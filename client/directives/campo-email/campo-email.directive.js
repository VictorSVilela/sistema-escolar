angular.module("TreinamentoApp")
    .directive("campoEmail", campoEmail);


function campoEmail() {
    return {
        templateUrl: 'directives/campo-email/campo-email.html',
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