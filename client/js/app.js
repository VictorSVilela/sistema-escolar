var app = angular.module("TreinamentoApp",['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'view/home.html',
            controller: 'homeController'
        })
        .state('cadastroAluno', {
            url: '/cadastroAluno',
            templateUrl: 'view/cadastroAluno.html',
            controller: 'alunoController'
        })
        .state('listarAluno', {
            url: '/listarAluno',
            templateUrl: 'view/listarAluno.html',
            controller: 'alunoController'
        });
});
