angular.module("TreinamentoApp").controller("UsuarioEditarController", UsuarioEditarController);

UsuarioEditarController.$inject = ['$scope', 'UsuarioService', '$stateParams'];

function UsuarioEditarController($scope, UsuarioService, $stateParams) {

    $scope.editarUsuario = editarUsuario;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.usuario = {};
        _consultar();
    }

    function _consultar(){
        UsuarioService.consultar($stateParams.id).then(response => {
            $scope.usuario = response.data;
        });
    }

    function editarUsuario(id, usuario) {
        UsuarioService.editarUsuario(id, usuario).then(response => {
            $scope.usuarios = response.data;;
        });
    }
}
