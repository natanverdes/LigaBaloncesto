'use strict';

angular.module('ligaBaloncestoApp')
    .factory('JugadorJuegaPartido', function ($resource, DateUtils) {
        return $resource('api/jugadorJuegaPartidos/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
