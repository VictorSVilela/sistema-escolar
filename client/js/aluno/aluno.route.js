angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('alunoEditar', {
                url: '/aluno/{id:int}',
                templateUrl: 'js/aluno/alunoIncluir.html',
                controller: 'AlunoEditarController'
            })
            .state('alunoIncluir', {
                url: '/aluno/incluir',
                templateUrl: 'js/aluno/alunoIncluir.html',
                controller: 'AlunoIncluirController'
            })
            .state('alunoListar', {
                url: '/aluno',
                templateUrl: 'js/aluno/alunoListar.html',
                controller: 'AlunoListarController'
            });
    });
