angular.module('TreinamentoApp')
    .filter('ativa', Ativa)

function Ativa(){
    return function (isAtiva){
        return isAtiva === true ? "Ativa" : "Inativa";
    }
}