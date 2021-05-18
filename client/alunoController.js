angular.module("TreinamentoApp").controller("alunoController", AlunoController);

AlunoController.$inject = ['$scope', '$http'];

function AlunoController($scope, $http) {

    $scope.adicionarAluno = adicionarAluno;
    $scope.deletarAluno = deletarAluno;
    $scope.editarAluno = editarAluno;

    _inicializar();

    ////////////////////////////////////////////////

    function _inicializar() {
        listar();
    }

    function listar(){
        $http.get('http://localhost:8080/rest/alunos').then(response => {
            $scope.alunos = response.data;
        });
    }

    function adicionarAluno(aluno) {
        $http.post('http://localhost:8080/rest/alunos', aluno).then(response => {
            $scope.alunos = response.data;
            delete $scope.aluno;
            $scope.alunoForm.$setPristine();
        });
    }

    function deletarAluno(id) {
        $http.delete(`http://localhost:8080/rest/alunos/${id}`).then(response => {
            $scope.alunos = $scope.alunos.filter(aluno => aluno.id !== id);
        });
    }

    function editarAluno(id, aluno) {
        $http.put(`http://localhost:8080/rest/alunos/${id}`, aluno).then(response => {
            listar();
        });
    }
}
