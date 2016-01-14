'use strict';

describe('Equipo Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockEquipo, MockEstadio, MockJugador, MockEntrenador, MockSocio, MockTemporada, MockPartido;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockEquipo = jasmine.createSpy('MockEquipo');
        MockEstadio = jasmine.createSpy('MockEstadio');
        MockJugador = jasmine.createSpy('MockJugador');
        MockEntrenador = jasmine.createSpy('MockEntrenador');
        MockSocio = jasmine.createSpy('MockSocio');
        MockTemporada = jasmine.createSpy('MockTemporada');
        MockPartido = jasmine.createSpy('MockPartido');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Equipo': MockEquipo,
            'Estadio': MockEstadio,
            'Jugador': MockJugador,
            'Entrenador': MockEntrenador,
            'Socio': MockSocio,
            'Temporada': MockTemporada,
            'Partido': MockPartido
        };
        createController = function() {
            $injector.get('$controller')("EquipoDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'ligaBaloncestoApp:equipoUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
