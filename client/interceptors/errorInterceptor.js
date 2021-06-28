angular.module("TreinamentoApp")
    .factory('errorInterceptor', errorInterceptor);

errorInterceptor.$inject = ['$q'];

function errorInterceptor($q) {
    return {
        request: function (config) {
            return config;
        },

        responseError: function httpError(error) {
            window.alert(error.data.mensagem);
            return $q.reject(error);
        }
    };
}