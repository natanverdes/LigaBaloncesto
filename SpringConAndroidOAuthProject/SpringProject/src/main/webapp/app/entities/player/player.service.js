(function() {
    'use strict';
    angular
        .module('basketballOauth2Jhipster3App')
        .factory('Player', Player);

    Player.$inject = ['$resource'];

    function Player ($resource) {
        var resourceUrl =  'api/players/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' },
            'topPlayers': {
                method: 'GET', isArray: true, url: 'api/players/top/:canastas',
                interceptor: {
                    response: function(response) {
                        response.resource.$httpHeaders = response.headers;
                        return response.resource;
                    }
                }
            },
            'topPlayersAsitencias': {
                method: 'GET', isArray: true, url: 'api/players/topasistencias/:asistencias',
                interceptor: {
                    response: function(response) {
                        response.resource.$httpHeaders = response.headers;
                        return response.resource;
                    }
                }
            }

        });
    }
})();
