angular.module("TreinamentoApp").factory("MateriaService", function ($http) {
    function _getMaterias() {
        return $http.get('http://localhost:8080/rest/materias');
    };

    function _incluirMateria(materia) {
        return $http.post('http://localhost:8080/rest/materias', materia)
            .then(response => {
                window.alert("Matéria cadastrada com sucesso");
                return response.data;
            });
    };

    function _deletarMateria(id) {
        return $http.delete(`http://localhost:8080/rest/materias/${id}`)
            .then(() => {
                window.alert("Matéria deletada com sucesso");
            });
    };

    function _editarMateria(id, materia) {
        return $http.put(`http://localhost:8080/rest/materias/${id}`, materia)
            .then(() => {
                window.alert("Matéria editada com sucesso");
            });
    };

    function _consultar(id) {
        return $http.get(`http://localhost:8080/rest/materias/${id}`);
    };

    return {
        getMaterias: _getMaterias,
        incluirMateria: _incluirMateria,
        deletarMateria: _deletarMateria,
        editarMateria: _editarMateria,
        consultar: _consultar
    };
});