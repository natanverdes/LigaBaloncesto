'use strict';

angular.module('ligaBaloncestoApp')
    .controller('EstadioDetailController', function ($scope, $rootScope, $stateParams, entity, Estadio, Equipo) {
        $scope.estadio = entity;
        $scope.load = function (id) {
            Estadio.get({id: id}, function(result) {
                $scope.estadio = result;
            });
        };
        var unsubscribe = $rootScope.$on('ligaBaloncestoApp:estadioUpdate', function(event, result) {
            $scope.estadio = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
