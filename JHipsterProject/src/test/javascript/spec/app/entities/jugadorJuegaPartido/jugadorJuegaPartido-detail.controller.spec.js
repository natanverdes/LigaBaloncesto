'use strict';

describe('JugadorJuegaPartido Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockJugadorJuegaPartido, MockJugador, MockPartido;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockJugadorJuegaPartido = jasmine.createSpy('MockJugadorJuegaPartido');
        MockJugador = jasmine.createSpy('MockJugador');
        MockPartido = jasmine.createSpy('MockPartido');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'JugadorJuegaPartido': MockJugadorJuegaPartido,
            'Jugador': MockJugador,
            'Partido': MockPartido
        };
        createController = function() {
            $injector.get('$controller')("JugadorJuegaPartidoDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'ligaBaloncestoApp:jugadorJuegaPartidoUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
