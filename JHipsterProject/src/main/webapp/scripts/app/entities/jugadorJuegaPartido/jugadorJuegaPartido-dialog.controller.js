'use strict';

angular.module('ligaBaloncestoApp').controller('JugadorJuegaPartidoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'JugadorJuegaPartido', 'Jugador', 'Partido',
        function($scope, $stateParams, $modalInstance, entity, JugadorJuegaPartido, Jugador, Partido) {

        $scope.jugadorJuegaPartido = entity;
        $scope.jugadors = Jugador.query();
        $scope.partidos = Partido.query();
        $scope.load = function(id) {
            JugadorJuegaPartido.get({id : id}, function(result) {
                $scope.jugadorJuegaPartido = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('ligaBaloncestoApp:jugadorJuegaPartidoUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.jugadorJuegaPartido.id != null) {
                JugadorJuegaPartido.update($scope.jugadorJuegaPartido, onSaveSuccess, onSaveError);
            } else {
                JugadorJuegaPartido.save($scope.jugadorJuegaPartido, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
