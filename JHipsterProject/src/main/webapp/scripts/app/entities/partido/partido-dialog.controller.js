'use strict';

angular.module('ligaBaloncestoApp').controller('PartidoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Partido', 'JugadorJuegaPartido', 'Equipo', 'Temporada', 'Arbitro',
        function($scope, $stateParams, $modalInstance, entity, Partido, JugadorJuegaPartido, Equipo, Temporada, Arbitro) {

        $scope.partido = entity;
        $scope.jugadorjuegapartidos = JugadorJuegaPartido.query();
        $scope.equipos = Equipo.query();
        $scope.temporadas = Temporada.query();
        $scope.arbitros = Arbitro.query();
        $scope.load = function(id) {
            Partido.get({id : id}, function(result) {
                $scope.partido = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('ligaBaloncestoApp:partidoUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.partido.id != null) {
                Partido.update($scope.partido, onSaveSuccess, onSaveError);
            } else {
                Partido.save($scope.partido, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
