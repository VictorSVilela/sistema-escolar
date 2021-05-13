angular.module("TreinamentoApp").controller("alunoController", AlunoController);

AlunoController.$inject = ['$scope', '$http'];

function AlunoController($scope, $http) {

    $http.get('http://localhost:8080/rest/alunos').then(response => {
        $scope.alunos = response.data;
    });

    $scope.adicionarAluno = function (aluno) {
        $http.post('http://localhost:8080/rest/alunos', aluno).then(response => {
            $scope.alunos = response.data;
            delete $scope.aluno;
            $scope.alunoForm.$setPristine();
        });
    };
}
