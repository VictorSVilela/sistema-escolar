angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('escolaIncluir', {
                url: '/escola/incluir',
                templateUrl: 'escola/escolaIncluir.html',
                controller: 'EscolaIncluirController'
            })
            .state('escolaListar', {
                url: '/escola',
                templateUrl: 'escola/escolaListar.html',
                controller: 'EscolaListarController'
            })
            .state('escolaEditar', {
                url: '/escola/{id}',
                templateUrl: 'escola/escolaEditar.html',
                controller: 'EscolaEditarController'
            });
    });