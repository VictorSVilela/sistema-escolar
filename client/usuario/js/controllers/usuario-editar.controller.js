angular.module("TreinamentoApp").controller("UsuarioEditarController", UsuarioEditarController);

UsuarioEditarController.$inject = ['$scope', 'UsuarioService', '$stateParams'];

function UsuarioEditarController($scope, UsuarioService, $stateParams) {

    $scope.editarUsuario = editarUsuario;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.editarUsuario = editarUsuario;
        $scope.usuario = {};
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        _consultar();
    }

    function _consultar(){
        UsuarioService.consultar($stateParams.id).then(response => {
            $scope.usuario = response.data;
        });
    }

    function editarUsuario(id, usuario) {
        UsuarioService.editarUsuario(id, usuario)
            .then(() => {
                $state.go('usuarioListar');
            }).catch(()=>{});
    }
}
