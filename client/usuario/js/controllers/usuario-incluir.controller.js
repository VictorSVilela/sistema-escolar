angular.module("TreinamentoApp").controller("UsuarioIncluirController", UsuarioIncluirController);

UsuarioIncluirController.$inject = ['$scope', 'UsuarioService', '$state'];

function UsuarioIncluirController($scope, UsuarioService, $state) {

    $scope.incluirUsuario = incluirUsuario;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        $scope.usuario ={};
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/
    }

    function incluirUsuario() {
        UsuarioService.incluirUsuario($scope.usuario).then(() => {
            $state.go('usuarioListar');
        }).catch(()=>{});
    }
}