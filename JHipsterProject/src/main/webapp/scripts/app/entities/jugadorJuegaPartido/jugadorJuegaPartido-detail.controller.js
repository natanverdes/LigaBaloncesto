'use strict';

angular.module('ligaBaloncestoApp')
    .controller('JugadorJuegaPartidoDetailController', function ($scope, $rootScope, $stateParams, entity, JugadorJuegaPartido, Jugador, Partido) {
        $scope.jugadorJuegaPartido = entity;
        $scope.load = function (id) {
            JugadorJuegaPartido.get({id: id}, function(result) {
                $scope.jugadorJuegaPartido = result;
            });
        };
        var unsubscribe = $rootScope.$on('ligaBaloncestoApp:jugadorJuegaPartidoUpdate', function(event, result) {
            $scope.jugadorJuegaPartido = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
