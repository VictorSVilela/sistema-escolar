angular.module("TreinamentoApp").controller("UsuarioListarController", UsuarioListarController);

UsuarioListarController.$inject = ['$scope', 'UsuarioService'];

function UsuarioListarController($scope, UsuarioService) {

    $scope.deletarUsuario = deletarUsuario;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        UsuarioService.getUsuarios().then(response => {
            $scope.usuarios = response;
        });
    }

    function deletarUsuario(id) {
        if (confirm(`Deseja deletar o usuário ${id}?`)) {
            UsuarioService.deletarUsuario(id).then(response => {
                $scope.usuarios = $scope.usuarios.filter(usuario => usuario.id !== id);
            });
        }
    }

}