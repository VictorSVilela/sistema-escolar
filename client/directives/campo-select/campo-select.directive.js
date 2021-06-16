angular.module("TreinamentoApp")
    .directive("campoSelect", campoSelect)

function campoSelect() {
    return {
        restrict: "E",
        templateUrl: 'directives/campo-select/campo-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            ngMultiple: "@",
            options: "=",
            defaultOption: "@"
        },
        link:function(scope,element,attr,ngModel) {

        }
    }
}