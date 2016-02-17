(function () {

    var app = angular.module('myApp', []);

    app.controller('ex2', function($scope){
        $scope.jugador = {
            nombre: "Nombre",
            canastas: "321",
            asistencias: "123"
        }
    });

    app.controller('ex4', function ($scope) {
        $scope.jugadores = [{
            id: 1,
            nombre: "Nombre",
            canastas: "321",
            asistencias: "123"
        },{
            id: 2,
            nombre: "Nombre2",
            canastas: "456",
            asistencias: "654"
        }, {
            id: 3,
            nombre: "Nombre3",
            canastas: "789",
            asistencias: "987"
        }]
    });













})();