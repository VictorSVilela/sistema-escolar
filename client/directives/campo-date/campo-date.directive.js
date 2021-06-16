angular.module("TreinamentoApp")
    .directive('campoDate', ["$filter", function($filter) {
    return {
        require: "ngModel",
        restrict: "E",
        link: function(scope, element, attrs, ngModelController) {
            if (element.attr("type") != "date") {
                return;
            }

            if (verificaSeNavegadorNaoSuportaInputDate()) {
                ngModelController.$parsers.push(function(dataEmString) {
                    return converteStringParaData(dataEmString);
                });

                ngModelController.$formatters.push(function(data) {
                    return formataDataParaString(data);
                });
            }
        }
    };
}]);
