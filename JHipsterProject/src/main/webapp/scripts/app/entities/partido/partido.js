'use strict';

angular.module('ligaBaloncestoApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('partido', {
                parent: 'entity',
                url: '/partidos',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ligaBaloncestoApp.partido.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/partido/partidos.html',
                        controller: 'PartidoController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('partido');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('partido.detail', {
                parent: 'entity',
                url: '/partido/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ligaBaloncestoApp.partido.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/partido/partido-detail.html',
                        controller: 'PartidoDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('partido');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Partido', function($stateParams, Partido) {
                        return Partido.get({id : $stateParams.id});
                    }]
                }
            })
            .state('partido.new', {
                parent: 'partido',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/partido/partido-dialog.html',
                        controller: 'PartidoDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    fecha: null,
                                    canastasEquipoLocal: null,
                                    canastasEquipoVisitante: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('partido', null, { reload: true });
                    }, function() {
                        $state.go('partido');
                    })
                }]
            })
            .state('partido.edit', {
                parent: 'partido',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/partido/partido-dialog.html',
                        controller: 'PartidoDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Partido', function(Partido) {
                                return Partido.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('partido', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('partido.delete', {
                parent: 'partido',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/partido/partido-delete-dialog.html',
                        controller: 'PartidoDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Partido', function(Partido) {
                                return Partido.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('partido', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
