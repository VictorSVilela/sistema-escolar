angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('alunoEditar', {
                url: '/aluno/{id}',
                templateUrl: 'aluno/alunoEditar.html',
                controller: 'AlunoEditarController'
            })
            .state('alunoIncluir', {
                url: '/aluno/incluir',
                templateUrl: 'aluno/alunoIncluir.html',
                controller: 'AlunoIncluirController'
            })
            .state('alunoListar', {
                url: '/aluno',
                templateUrl: 'aluno/alunoListar.html',
                controller: 'AlunoListarController'
            });
    });