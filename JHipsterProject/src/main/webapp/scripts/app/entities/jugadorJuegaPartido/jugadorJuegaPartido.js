'use strict';

angular.module('ligaBaloncestoApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('jugadorJuegaPartido', {
                parent: 'entity',
                url: '/jugadorJuegaPartidos',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ligaBaloncestoApp.jugadorJuegaPartido.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jugadorJuegaPartido/jugadorJuegaPartidos.html',
                        controller: 'JugadorJuegaPartidoController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('jugadorJuegaPartido');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('jugadorJuegaPartido.detail', {
                parent: 'entity',
                url: '/jugadorJuegaPartido/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ligaBaloncestoApp.jugadorJuegaPartido.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/jugadorJuegaPartido/jugadorJuegaPartido-detail.html',
                        controller: 'JugadorJuegaPartidoDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('jugadorJuegaPartido');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'JugadorJuegaPartido', function($stateParams, JugadorJuegaPartido) {
                        return JugadorJuegaPartido.get({id : $stateParams.id});
                    }]
                }
            })
            .state('jugadorJuegaPartido.new', {
                parent: 'jugadorJuegaPartido',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/jugadorJuegaPartido/jugadorJuegaPartido-dialog.html',
                        controller: 'JugadorJuegaPartidoDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    canastas: null,
                                    asistencias: null,
                                    rebotes: null,
                                    faltas: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('jugadorJuegaPartido', null, { reload: true });
                    }, function() {
                        $state.go('jugadorJuegaPartido');
                    })
                }]
            })
            .state('jugadorJuegaPartido.edit', {
                parent: 'jugadorJuegaPartido',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/jugadorJuegaPartido/jugadorJuegaPartido-dialog.html',
                        controller: 'JugadorJuegaPartidoDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['JugadorJuegaPartido', function(JugadorJuegaPartido) {
                                return JugadorJuegaPartido.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('jugadorJuegaPartido', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('jugadorJuegaPartido.delete', {
                parent: 'jugadorJuegaPartido',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/jugadorJuegaPartido/jugadorJuegaPartido-delete-dialog.html',
                        controller: 'JugadorJuegaPartidoDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['JugadorJuegaPartido', function(JugadorJuegaPartido) {
                                return JugadorJuegaPartido.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('jugadorJuegaPartido', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
