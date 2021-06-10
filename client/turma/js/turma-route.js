angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('turmaIncluir', {
                url: '/turma/incluir',
                templateUrl: 'turma/turmaIncluir.html',
                controller: 'TurmaIncluirController'
            })
            .state('turmaListar', {
                url: '/turma',
                templateUrl: 'turma/turmaListar.html',
                controller: 'TurmaListarController'
            })
            .state('turmaEditar', {
                url: '/turma/{id}',
                templateUrl: 'turma/turmaEditar.html',
                controller: 'TurmaEditarController'
            });
    });