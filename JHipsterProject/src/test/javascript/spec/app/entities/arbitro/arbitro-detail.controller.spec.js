'use strict';

describe('Arbitro Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockArbitro, MockLiga, MockPartido;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockArbitro = jasmine.createSpy('MockArbitro');
        MockLiga = jasmine.createSpy('MockLiga');
        MockPartido = jasmine.createSpy('MockPartido');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Arbitro': MockArbitro,
            'Liga': MockLiga,
            'Partido': MockPartido
        };
        createController = function() {
            $injector.get('$controller')("ArbitroDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'ligaBaloncestoApp:arbitroUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
