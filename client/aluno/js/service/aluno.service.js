angular.module("TreinamentoApp").factory("AlunoService", function ($http) {
    function _getAlunos() {
        return $http.get('http://localhost:8080/rest/alunos');
    };

    function _adicionarAluno(aluno) {
        return $http.post('http://localhost:8080/rest/alunos', aluno);
    };

    function _deletarAluno(id) {
        return $http.delete(`http://localhost:8080/rest/alunos/${id}`);
    };

    function _editarAluno(id, aluno) {
        return $http.put(`http://localhost:8080/rest/alunos/${id}`, aluno);
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