'use strict';

angular.module('ligaBaloncestoApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('estadio', {
                parent: 'entity',
                url: '/estadios',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ligaBaloncestoApp.estadio.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/estadio/estadios.html',
                        controller: 'EstadioController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('estadio');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('estadio.detail', {
                parent: 'entity',
                url: '/estadio/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'ligaBaloncestoApp.estadio.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/estadio/estadio-detail.html',
                        controller: 'EstadioDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('estadio');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Estadio', function($stateParams, Estadio) {
                        return Estadio.get({id : $stateParams.id});
                    }]
                }
            })
            .state('estadio.new', {
                parent: 'estadio',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/estadio/estadio-dialog.html',
                        controller: 'EstadioDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    nombre: null,
                                    localidad: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('estadio', null, { reload: true });
                    }, function() {
                        $state.go('estadio');
                    })
                }]
            })
            .state('estadio.edit', {
                parent: 'estadio',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/estadio/estadio-dialog.html',
                        controller: 'EstadioDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Estadio', function(Estadio) {
                                return Estadio.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('estadio', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('estadio.delete', {
                parent: 'estadio',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/estadio/estadio-delete-dialog.html',
                        controller: 'EstadioDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Estadio', function(Estadio) {
                                return Estadio.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('estadio', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
