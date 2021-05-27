angular.module("TreinamentoApp").factory("CursoService", function ($http) {
    function _getCursos() {
        return $http.get('http://localhost:8080/rest/cursos');
    };

    function _incluirCurso(curso) {
        return $http.post('http://localhost:8080/rest/cursos', curso);
    };

    function _deletarCurso(id) {
        return $http.delete(`http://localhost:8080/rest/cursos/${id}`);
    };

    function _editarCurso(id, curso) {
        return $http.put(`http://localhost:8080/rest/cursos/${id}`, curso);
    };

    function _consultar(id) {
        return $http.get(`http://localhost:8080/rest/cursos/${id}`);
    };

    return {
        getCursos: _getCursos,
        incluirCurso: _incluirCurso,
        deletarCurso: _deletarCurso,
        editarCurso: _editarCurso,
        consultar: _consultar
    };
});