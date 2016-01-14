'use strict';

angular.module('ligaBaloncestoApp')
    .factory('Temporada', function ($resource, DateUtils) {
        return $resource('api/temporadas/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.ano = DateUtils.convertLocaleDateFromServer(data.ano);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.ano = DateUtils.convertLocaleDateToServer(data.ano);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.ano = DateUtils.convertLocaleDateToServer(data.ano);
                    return angular.toJson(data);
                }
            }
        });
    });
