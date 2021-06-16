angular.module("TreinamentoApp").factory("UsuarioService", function ($http) {
    function _getUsuarios() {
        return $http.get('http://localhost:8080/rest/usuarios');
    };

    function _incluirUsuario(usuario) {
        return $http.post('http://localhost:8080/rest/usuarios', usuario)
            .then(response => {
                window.alert("Usuário cadastrado com sucesso");
                return response.data;
            });
    };

    function _deletarUsuario(id) {
        return $http.delete(`http://localhost:8080/rest/usuarios/${id}`)
            .then(() => {
                window.alert("Usuário deletado com sucesso");
            });
    };

    function _editarUsuario(id, usuario) {
        return $http.put(`http://localhost:8080/rest/usuarios/${id}`, usuario)
            .then(() => {
                window.alert("Usuário editado com sucesso");
            });
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