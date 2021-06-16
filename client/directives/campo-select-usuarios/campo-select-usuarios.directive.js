angular.module("TreinamentoApp")
    .directive("campoSelectUsuarios", campoSelectUsuarios)

function campoSelectUsuarios() {
    campoSelectUsuariosController.$inject =['$scope','UsuarioService'];
    return {
        restrict: "E",
        templateUrl: 'directives/campo-select/campo-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
        },
        controller: campoSelectUsuariosController
    }

    function campoSelectUsuariosController($scope,UsuarioService){
        _incializar();

        function _incializar(){
            $scope.defaultOptionText = 'Selecione...';
            $scope.options=[];
            UsuarioService.getUsuarios()
                .then(usuarios=>{
                    $scope.options = usuarios;
                })
        }
    }

}