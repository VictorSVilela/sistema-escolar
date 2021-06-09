angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('cursoIncluir', {
                url: '/curso/incluir',
                templateUrl: 'curso/cursoIncluir.html',
                controller: 'CursoIncluirController'
            })
            .state('cursoListar', {
                url: '/curso',
                templateUrl: 'curso/cursoListar.html',
                controller: 'CursoListarController'
            })
            .state('cursoEditar', {
                url: '/curso/{id}',
                templateUrl: 'curso/cursoEditar.html',
                controller: 'CursoEditarController'
            });
    });