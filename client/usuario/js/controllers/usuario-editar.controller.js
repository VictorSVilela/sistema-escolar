angular.module("TreinamentoApp").controller("UsuarioEditarController", UsuarioEditarController);

UsuarioEditarController.$inject = ['$scope', '$state', 'UsuarioService', '$stateParams'];

function UsuarioEditarController($scope, $state, UsuarioService, $stateParams) {

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
        }).catch(()=>{
            $state.go('usuarioListar');
        });
    }

    function editarUsuario() {
        UsuarioService.editarUsuario($scope.usuario)
            .then(() => {
                $state.go('usuarioListar');
            }).catch(()=>{});
    }
}
