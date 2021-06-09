angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('usuarioIncluir', {
                url: '/usuario/incluir',
                templateUrl: 'usuario/usuarioIncluir.html',
                controller: 'UsuarioIncluirController'
            })
            .state('usuarioListar', {
                url: '/usuario',
                templateUrl: 'usuario/usuarioListar.html',
                controller: 'UsuarioListarController'
            })
            .state('usuarioEditar', {
                url: '/usuario/{id}',
                templateUrl: 'usuario/usuarioEditar.html',
                controller: 'UsuarioEditarController'
            });
    });