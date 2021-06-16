angular.module("TreinamentoApp")
    .directive("campoDate", ["$filter", function($filter) {
        return {
            require: 'ngModel',
            link: function(scope, element, attrs, ngModelController) {
                if (element.attr("type") != "date") {
                    return;
                }
                ngModelController.$parsers.push(function(data) {
                    //View -> Model
                    return data;
                });
                ngModelController.$formatters.push(function(data) {
                    //Model -> View
                    return $filter('date')(data, "DD-MM-YYYY");
                });
            }
        }
    }
]);
