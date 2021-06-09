angular.module("TreinamentoApp")
    .config(function ($stateProvider) {

        $stateProvider
            .state('materiaIncluir', {
                url: '/materia/incluir',
                templateUrl: 'materia/materiaIncluir.html',
                controller: 'MateriaIncluirController'
            })
            .state('materiaListar', {
                url: '/materia',
                templateUrl: 'materia/materiaListar.html',
                controller: 'MateriaListarController'
            })
            .state('materiaEditar', {
                url: '/materia/{id}',
                templateUrl: 'materia/materiaEditar.html',
                controller: 'MateriaEditarController'
            });
    });