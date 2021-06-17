angular.module("TreinamentoApp").factory("TurmaService", function ($http) {
    function _getTurmas() {
        return $http.get('http://localhost:8080/rest/turmas')
            .then(response => {
                return response.data;
            });
    }

    function _incluirTurma(turma) {
        return $http.post('http://localhost:8080/rest/turmas', turma)
            .then(response => {
                window.alert("Turma cadastrado com sucesso");
                return response.data;
            });
    }

    function _deletarTurma(id) {
        return $http.delete(`http://localhost:8080/rest/turmas/${id}`)
            .then(() => {
                window.alert('Turma deletada com sucesso');
            });
    }

    function _editarTurma(id, turma) {
        return $http.put(`http://localhost:8080/rest/turmas/${id}`, turma)
            .then(() => {
                window.alert('Turma editada com sucesso');
            });
    }

    function _consultar(id) {
        return $http.get(`http://localhost:8080/rest/turmas/${id}`);
    }

    function _criarMatricula(sigla,id) {
        return $http.get(`http://localhost:8080/rest/turmas/sequencia/${sigla}?id=${id}`);
    }

    return {
        getTurmas: _getTurmas,
        incluirTurma: _incluirTurma,
        deletarTurma: _deletarTurma,
        editarTurma: _editarTurma,
        consultar: _consultar,
        criarMatricula: _criarMatricula
    };
});