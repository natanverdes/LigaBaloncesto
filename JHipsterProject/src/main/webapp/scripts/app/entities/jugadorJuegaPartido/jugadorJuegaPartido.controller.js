'use strict';

angular.module('ligaBaloncestoApp')
    .controller('JugadorJuegaPartidoController', function ($scope, $state, $modal, JugadorJuegaPartido, ParseLinks) {
      
        $scope.jugadorJuegaPartidos = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            JugadorJuegaPartido.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.jugadorJuegaPartidos = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.jugadorJuegaPartido = {
                canastas: null,
                asistencias: null,
                rebotes: null,
                faltas: null,
                id: null
            };
        };
    });
