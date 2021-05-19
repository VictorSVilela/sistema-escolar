angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('alunoEditar', {
                url: '/aluno/{id}',
                templateUrl: 'aluno/alunoIncluir.html',
                controller: 'AlunoEditarController',
                resolve: {
                    aluno: function (AlunoService, $state) {
                        return AlunoService.consultar($state.id);
                    }
                }
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