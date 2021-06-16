angular.module("TreinamentoApp")
    .directive("campoTextarea", campoTextarea)

function campoTextarea() {
    campoTextareaController.$inject =['$scope'];
    return {
        restrict: "E",
        templateUrl: 'directives/campo-textarea/campo-textarea.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            pattern: "=?",
            max: "@?"

        },
        controller: campoTextareaController
    }

    function campoTextareaController($scope){
        _incializar();

        function _incializar(){

        }
    }

}