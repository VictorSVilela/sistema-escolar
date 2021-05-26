angular.module("TreinamentoApp").factory("EscolaService", function ($http) {
    function _getEscolas() {
        return $http.get('http://localhost:8080/rest/escolas');
    };

    function _incluirEscola(escola) {
        return $http.post('http://localhost:8080/rest/escolas', escola);
    };

    function _deletarEscola(id) {
        return $http.delete(`http://localhost:8080/rest/escolas/${id}`);
    };

    function _editarEscola(id, escola) {
        return $http.put(`http://localhost:8080/rest/escolas/${id}`, escola);
    };

    function _consultar(id) {
        return $http.get(`http://localhost:8080/rest/escolas/${id}`);
    };

    return {
        getEscolas: _getEscolas,
        incluirEscola: _incluirEscola,
        deletarEscola: _deletarEscola,
        editarEscola: _editarEscola,
        consultar: _consultar
    };
});