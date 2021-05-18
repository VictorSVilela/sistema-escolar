angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('alunoEditar', {
                url: '/aluno/{id:int}',
                templateUrl: 'aluno/alunoIncluir.html',
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
