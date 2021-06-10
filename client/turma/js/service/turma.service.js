angular.module("TreinamentoApp").factory("TurmaService", function ($http) {
    function _getTurmas() {
        return $http.get('http://localhost:8080/rest/turmas');
    };

    function _incluirTurma(turma) {
        return $http.post('http://localhost:8080/rest/turmas', turma)
            .then(response => {
                window.alert("Turma cadastrado com sucesso");
                return response.data;
            });
    };

    function _deletarTurma(id) {
        return $http.delete(`http://localhost:8080/rest/turmas/${id}`);
    };

    function _editarTurma(id, turma) {
        return $http.put(`http://localhost:8080/rest/turmas/${id}`, turma);
    };

    function _consultar(id) {
        return $http.get(`http://localhost:8080/rest/turmas/${id}`);
    };

    return {
        getTurmas: _getTurmas,
        incluirTurma: _incluirTurma,
        deletarTurma: _deletarTurma,
        editarTurma: _editarTurma,
        consultar: _consultar
    };
});