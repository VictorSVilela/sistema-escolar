angular.module("TreinamentoApp").controller("UsuarioIncluirController", UsuarioIncluirController);

UsuarioIncluirController.$inject = ['$scope', 'UsuarioService'];

function UsuarioIncluirController($scope, UsuarioService) {

    $scope.incluirUsuario = incluirUsuario;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        UsuarioService.getUsuarios().then(response => {
            $scope.usuarios = response.data;
        });
    }

    function incluirUsuario(usuario) {
        UsuarioService.incluirUsuario(usuario).then(response => {
            $scope.usuarios = response.data;
            listar();
        });
    }
}