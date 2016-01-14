'use strict';

describe('Temporada Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockTemporada, MockEquipo, MockPartido, MockLiga;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockTemporada = jasmine.createSpy('MockTemporada');
        MockEquipo = jasmine.createSpy('MockEquipo');
        MockPartido = jasmine.createSpy('MockPartido');
        MockLiga = jasmine.createSpy('MockLiga');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Temporada': MockTemporada,
            'Equipo': MockEquipo,
            'Partido': MockPartido,
            'Liga': MockLiga
        };
        createController = function() {
            $injector.get('$controller')("TemporadaDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'ligaBaloncestoApp:temporadaUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
