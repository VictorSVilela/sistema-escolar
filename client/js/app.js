angular.module("TreinamentoApp",['ui.router']).config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'view/home.html',
            controller: 'homeController'
        })
});
