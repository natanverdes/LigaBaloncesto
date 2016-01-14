'use strict';

angular.module('ligaBaloncestoApp')
	.controller('JugadorJuegaPartidoDeleteController', function($scope, $modalInstance, entity, JugadorJuegaPartido) {

        $scope.jugadorJuegaPartido = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            JugadorJuegaPartido.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });