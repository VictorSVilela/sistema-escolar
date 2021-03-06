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

    function _editarTurma(turma) {
        return $http.put(`http://localhost:8080/rest/turmas/${turma.id}`, turma)
            .then(() => {
                window.alert('Turma editada com sucesso');
            });
    }

    function _consultar(id) {
        return $http.get(`http://localhost:8080/rest/turmas/${id}`);
    }

    function _gerarMatricula(cursoId) {
        return $http.get(`http://localhost:8080/rest/turmas/gerar-matricula/${cursoId}`);
    }

    return {
        getTurmas: _getTurmas,
        incluirTurma: _incluirTurma,
        deletarTurma: _deletarTurma,
        editarTurma: _editarTurma,
        consultar: _consultar,
        gerarMatricula: _gerarMatricula
    };
});