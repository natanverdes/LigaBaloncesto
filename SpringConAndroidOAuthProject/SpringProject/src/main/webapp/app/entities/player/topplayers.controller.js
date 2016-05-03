(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('TopPlayersController', TopPlayersController);

    TopPlayersController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Player', 'ParseLinks'];

    function TopPlayersController ($scope, $rootScope, $stateParams, entity, Player, ParseLinks) {

        entity.$promise.then(function(data){
            console.log(data);
            var link = entity.$httpHeaders('link');

            $scope.links = ParseLinks.parse(link);

            $scope.savedPlayers = data;
        });

    }
})();
