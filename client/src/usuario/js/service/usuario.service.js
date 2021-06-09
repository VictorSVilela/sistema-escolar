angular.module("TreinamentoApp").factory("UsuarioService", function ($http) {
    function _getUsuarios() {
        return $http.get('http://localhost:8080/rest/usuarios');
    };

    function _incluirUsuario(usuario) {
        return $http.post('http://localhost:8080/rest/usuarios', usuario);
    };

    function _deletarUsuario(id) {
        return $http.delete(`http://localhost:8080/rest/usuarios/${id}`);
    };

    function _editarUsuario(id, usuario) {
        return $http.put(`http://localhost:8080/rest/usuarios/${id}`, usuario);
    };

    function _consultar(id) {
        return $http.get(`http://localhost:8080/rest/usuarios/${id}`);
    };

    return {
        getUsuarios: _getUsuarios,
        incluirUsuario: _incluirUsuario,
        deletarUsuario: _deletarUsuario,
        editarUsuario: _editarUsuario,
        consultar: _consultar
    };
});