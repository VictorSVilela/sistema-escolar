angular.module("TreinamentoApp")
    .directive("formInput", formInput);


function formInput() {
    return {
        templateUrl: './directives/form-input/form-input.html',
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