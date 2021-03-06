angular.module("TreinamentoApp").factory("AlunoService", function ($http) {
    function _getAlunos() {
        return $http.get('http://localhost:8080/rest/alunos')
            .then(response => {
                return response.data;
            });
    };

    function _adicionarAluno(aluno) {
        return $http.post('http://localhost:8080/rest/alunos', aluno)
            .then(response => {
                window.alert("Aluno cadastrado com sucesso");
                return response.data;
            });
    };

    function _deletarAluno(id) {
        return $http.delete(`http://localhost:8080/rest/alunos/${id}`)
            .then(() => {
                window.alert("Aluno deletado com sucesso");
            });
    };

    function _editarAluno(aluno) {
        return $http.put(`http://localhost:8080/rest/alunos/${aluno.id}`, aluno)
            .then(() => {
                window.alert("Aluno editado com sucesso");
            });
    };

    function _consultar(id) {
        return $http.get(`http://localhost:8080/rest/alunos/${id}`);
    };

    return {
        getALunos: _getAlunos,
        adicionarAluno: _adicionarAluno,
        deletarAluno: _deletarAluno,
        editarAluno: _editarAluno,
        consultar: _consultar
    };


});