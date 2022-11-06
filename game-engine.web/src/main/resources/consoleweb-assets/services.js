/*
 *    Copyright 2015 Fondazione Bruno Kessler - Trento RISE
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

angular.module('gamificationEngine.services', [])
	.factory('gamesFactory', function ($rootScope, $http, $q, $timeout) {
		// Games data operations factory
		var url = "..";

		var setUrl = function (urlPrefix) {
			url = urlPrefix;
		}

		// Get games
		var getGames = function () {
			
			var deferred = $q.defer();

			// If games haven't been already loaded
			if (!$rootScope.games || $rootScope.games.length === 0) {
				// Load games
				$http.get(url + `/console/game`).success(function (data) {
					$rootScope.games = data;
					deferred.resolve();
				}).error(function () {
					deferred.reject();
				});
			} else {
				deferred.resolve();
			}
			return deferred.promise;
		};
		
		var getGamesByDomain = function () {
			
			var deferred = $q.defer();

			// If games haven't been already loaded
			if (!$rootScope.games || $rootScope.games.length === 0) {
				// Load games
				$http.get(url + `/console/game-by-domain`).success(function (data) {
					$rootScope.games = data;
					deferred.resolve();
				}).error(function () {
					deferred.reject();
				});
			} else {
				deferred.resolve();
			}
			return deferred.promise;
		};

		// Get game by ID
		var getGameById = function (id) {
			var deferred = $q.defer();
			$http.get(url + `/console/game/${id}`) .success(function (data) {
				deferred.resolve(data);
			}).error(function () {
				deferred.reject();
			});
			return deferred.promise;
		};

		// Get game by name
		var existGameWithName = function (name) {
			const exists = $rootScope.games.filter(game => game.name == name).length > 0;
			return exists;
		};

		// Boolean. Returns whether exists or not an instance by its name
		var existsInstanceByName = function (game, instanceName, instanceType) {
			var found = false;
			var a = [];
			if (instanceType === 'points') {
				a = game.pointConcept;
			}

			if (instanceType === 'badges_collections') {
				a = game.badgeCollectionConcept;
			}
			angular.forEach(a, function (i) {
				if (!found && i.name === instanceName) {
					found = true;
				}
			});
			return found;
		};

		var getPoints = function (gameId) {
			var deferred = $q.defer();

			$http.get(url + `/console/game/${gameId}/point`).
			success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).
			error(function (data, status, headers, config) {
				deferred.reject();
			});

			return deferred.promise;
		}

		var getBadges = function (gameId) {
			var deferred = $q.defer();

			$http.get(url + `/console/game/${gameId}/badgecoll`).
			success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).
			error(function (data, status, headers, config) {
				deferred.reject();
			});

			return deferred.promise;
		}

		var addTask = function (game, task) {
			task.type = 'general';
			// ^\s*($|#|\w+\s*=|(\?|\*|(?:[0-5]?\d)(?:(?:-|\/|\,)(?:[0-5]?\d))?(?:,(?:[0-5]?\d)(?:(?:-|\/|\,)(?:[0-5]?\d))?)*)\s+(\?|\*|(?:[0-5]?\d)(?:(?:-|\/|\,)(?:[0-5]?\d))?(?:,(?:[0-5]?\d)(?:(?:-|\/|\,)(?:[0-5]?\d))?)*)\s+(\?|\*|(?:[01]?\d|2[0-3])(?:(?:-|\/|\,)(?:[01]?\d|2[0-3]))?(?:,(?:[01]?\d|2[0-3])(?:(?:-|\/|\,)(?:[01]?\d|2[0-3]))?)*)\s+(\?|\*|(?:0?[1-9]|[12]\d|3[01])(?:(?:-|\/|\,)(?:0?[1-9]|[12]\d|3[01]))?(?:,(?:0?[1-9]|[12]\d|3[01])(?:(?:-|\/|\,)(?:0?[1-9]|[12]\d|3[01]))?)*)\s+(\?|\*|(?:[1-9]|1[012])(?:(?:-|\/|\,)(?:[1-9]|1[012]))?(?:L|W)?(?:,(?:[1-9]|1[012])(?:(?:-|\/|\,)(?:[1-9]|1[012]))?(?:L|W)?)*|\?|\*|(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?(?:,(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?)*)\s+(\?|\*|(?:[0-6])(?:(?:-|\/|\,|#)(?:[0-6]))?(?:L)?(?:,(?:[0-6])(?:(?:-|\/|\,|#)(?:[0-6]))?(?:L)?)*|\?|\*|(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?(?:,(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?)*)(|\s)+(\?|\*|(?:|\d{4})(?:(?:-|\/|\,)(?:|\d{4}))?(?:,(?:|\d{4})(?:(?:-|\/|\,)(?:|\d{4}))?)*))$
			// reg exp for cron validation
			var deferred = $q.defer();
			$http.post(url + `/console/game/${game.id}/task`, task).
			success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).
			error(function (data, status, headers, config) {
				deferred.reject('msg_task_error');
			});

			return deferred.promise;
		}

		var editTask = function (game, task) {
			// ^\s*($|#|\w+\s*=|(\?|\*|(?:[0-5]?\d)(?:(?:-|\/|\,)(?:[0-5]?\d))?(?:,(?:[0-5]?\d)(?:(?:-|\/|\,)(?:[0-5]?\d))?)*)\s+(\?|\*|(?:[0-5]?\d)(?:(?:-|\/|\,)(?:[0-5]?\d))?(?:,(?:[0-5]?\d)(?:(?:-|\/|\,)(?:[0-5]?\d))?)*)\s+(\?|\*|(?:[01]?\d|2[0-3])(?:(?:-|\/|\,)(?:[01]?\d|2[0-3]))?(?:,(?:[01]?\d|2[0-3])(?:(?:-|\/|\,)(?:[01]?\d|2[0-3]))?)*)\s+(\?|\*|(?:0?[1-9]|[12]\d|3[01])(?:(?:-|\/|\,)(?:0?[1-9]|[12]\d|3[01]))?(?:,(?:0?[1-9]|[12]\d|3[01])(?:(?:-|\/|\,)(?:0?[1-9]|[12]\d|3[01]))?)*)\s+(\?|\*|(?:[1-9]|1[012])(?:(?:-|\/|\,)(?:[1-9]|1[012]))?(?:L|W)?(?:,(?:[1-9]|1[012])(?:(?:-|\/|\,)(?:[1-9]|1[012]))?(?:L|W)?)*|\?|\*|(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?(?:,(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?)*)\s+(\?|\*|(?:[0-6])(?:(?:-|\/|\,|#)(?:[0-6]))?(?:L)?(?:,(?:[0-6])(?:(?:-|\/|\,|#)(?:[0-6]))?(?:L)?)*|\?|\*|(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?(?:,(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?)*)(|\s)+(\?|\*|(?:|\d{4})(?:(?:-|\/|\,)(?:|\d{4}))?(?:,(?:|\d{4})(?:(?:-|\/|\,)(?:|\d{4}))?)*))$
			// reg exp for cron validation
			var deferred = $q.defer();
			$http.put(url + `/console/game/${game.id}/task`, task).
			success(function (data, status, headers, config) {
				deferred.resolve();
			}).
			error(function (data, status, headers, config) {
				deferred.reject('msg_task_error');
			});

			return deferred.promise;
		}

		var deleteTask = function (game, task) {
			var deferred = $q.defer();
			$http.post(url + `/console/game/${game.id}/task/del`, task).
			success(function (data, status, headers, config) {
				deferred.resolve();
			}).
			error(function (data, status, headers, config) {
				deferred.reject('msg_task_error');
			});

			return deferred.promise;
		}
		
		
		var addIncrementalClassification = function(game, classification) {
			var deferred = $q.defer();
			$http.post(url + `/model/game/${game.id}/incclassification`, classification).
			success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).
			error(function (data, status, headers, config) {
				deferred.reject('msg_task_error');
			});

			return deferred.promise;
		}
		
		var deleteIncrementalClassification = function (game, classification) {
			var deferred = $q.defer();
			$http.delete(url + `/model/game/${game.id}/incclassification/${classification.name}`).
			success(function (data, status, headers, config) {
				deferred.resolve();
			}).
			error(function (data, status, headers, config) {
				deferred.reject('msg_task_error');
			});

			return deferred.promise;
		}
		
		var editIncrementalClassification = function (game, classification) {
			var deferred = $q.defer();
			$http.put(url + `/model/game/${game.id}/incclassification/${classification.name}`, classification).
			success(function (data, status, headers, config) {
				deferred.resolve();
			}).
			error(function (data, status, headers, config) {
				deferred.reject('msg_task_error');
			});

			return deferred.promise;
		}
		
		

		var saveGame = function (game) {
			var deferred = $q.defer();

			$http.post(url + `/console/game`, game).
			success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).
			error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});

			return deferred.promise;
		};

		// Add or edit game
		var editGame = function (game, fields) {
			var deferred = $q.defer();

				if (!game.id) {
					game = {};
				}
				game.name = fields.name.trim();
				game.expiration = fields.expiration;
				game.domain = fields.domain ? fields.domain.trim() : fields.domain;
				game.notifyPCName = fields.notifyPCName;
				if(fields.challengeSettings) {
					if(!game.settings) {
						game.settings = {};
					}
					game.settings.challengeSettings = fields.challengeSettings;
				}
				
				$http.post(url + `/console/game`, game).
				success(function (data, status, headers, config) {
					if (!game.id) {
						$rootScope.games.unshift(data);
					}
					deferred.resolve(data);
				}).
				error(function (data, status, headers, config) {
					deferred.reject('msg_generic_error');
				});
			return deferred.promise;
		};

		// Add or edit instance
		var editInstance = function (game, instanceType, instanceProperties) {
			var deferred = $q.defer();

			// Create new instance
			var id = 1;
			angular.forEach(game.concepts, function (i) {
				if (i.id > id) {
					id = i.id;
				}
				id++;
			});


			var instance = {
					'id': id,
					'name': instanceProperties.name
			};

			var tmpGame = angular.copy(game);

			// Choose instance object structure
			if (instanceType == 'points') {
				let periods = {};
				let dayMillis = 24 * 3600 * 1000;
				angular.forEach(instanceProperties.periods,function(value,key){
					const endDate = value.end ? value.end.getTime() : undefined;
					const period = value.period ? value.period * dayMillis : undefined
					periods[value.name] = {start: value.start.getTime(),end: endDate, period: period, identifier :value.name, capacity: value.capacity};
				});
				instance.periods = periods;
				tmpGame.pointConcept.unshift(instance);
			} else if (instanceType == 'badges_collections') {
				tmpGame.badgeCollectionConcept.unshift(instance);
			}

			$http.post(url + `/console/game`, tmpGame).success(function (data, status, headers, config) {

				deferred.resolve(instance);
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});

			return deferred.promise;
		};

		// Delete game
		var deleteGame = function (game) {
			var deferred = $q.defer();

			$http.delete(url + `/console/game/${game.id}`).success(function (data, status, headers, config) {
				angular.forEach($rootScope.games, function (g, index) {
					if (g.id == game.id) {
						$rootScope.games.splice(index, 1);
						deferred.resolve();
					}
				});
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_delete_error');
			});

			return deferred.promise;
		};

		var getPlayersState = function (gameId, playerFilter, pageRequest, pageSize) {
			var deferred = $q.defer();
			$http.get(url + `/admin/data/game/${gameId}/player`, {
				params: {
					page: pageRequest,
					size: pageSize,
					playerFilter: playerFilter
				}
			}).success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});

			return deferred.promise;
		}
		
		var playersSearch = function (gameId, playerFilter, pageRequest, pageSize) {
			var deferred = $q.defer();
			var query = {};
			query.rawQuery = {};
			query.rawQuery.query = {};
			query.rawQuery.query = {'playerId' : '/' +playerFilter+'/'}
			$http.post(url + `/data/game/${gameId}/player/search`,query, {
				params: {
					page: pageRequest,
					size: pageSize,
				}
			}).success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});

			return deferred.promise;
		}
		
		var saveChallengeModel = function(gameId, model) {
			var deferred = $q.defer();
			model.variables = [];
			angular.copy(model.fields , model.variables);
			delete model.fields;
			$http.post(url + `/model/game/${gameId}/challenge`, model).success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});

			return deferred.promise;
		}
		
		var readChallengeModels = function(gameId) {
			var deferred = $q.defer();
			$http.get(url + `/model/game/${gameId}/challenge`).success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});

			return deferred.promise;
		}
		
		var deleteChallengeModel = function(gameId,modelId) {
			var deferred = $q.defer();
			$http.delete(url + `/model/game/${gameId}/challenge/${modelId}`).success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});

			return deferred.promise;
		}

		const deleteChallenge = function (gameId, playerId, challenge) {
			var deferred = $q.defer();

			$http.delete(url + `/data/game/${gameId}/player/${playerId}/challenge/${challenge.name}`).success(function (data, status, headers, config) {
				deferred.resolve();
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});
			return deferred.promise;
		}
		
		const updateChallenge = function (gameId, playerId, challengeUpdate) {
			var deferred = $q.defer();

			$http.put(url + `/data/game/${gameId}/player/${playerId}/challenge/${challengeUpdate.name}`, challengeUpdate).success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).error(function (data, status, headers, config) {
				if(status == 400) {
					deferred.reject(data.message);
				}
			});
			return deferred.promise;
		}
		
		

		// Get game by name
		var userProfile = function () {
			var deferred = $q.defer();
			$http.get(`../userProfile/`).success(function (data, status, headers, config) {
				deferred.resolve(data);
			}).error(function (data, status, headers, config) {
				deferred.reject('msg_generic_error');
			});

			return deferred.promise;
		};
		
		return {
			getGamesByDomain,
			'getGames': getGames,
			'getGameById': getGameById,
			'existGameWithName': existGameWithName,
			'editGame': editGame,
			'existsInstanceByName': existsInstanceByName,
			'editInstance': editInstance,
			'deleteGame': deleteGame,
			'saveGame': saveGame,
			'getPoints': getPoints,
			'getBadges': getBadges,
			'addTask': addTask,
			'deleteTask': deleteTask,
			'editTask': editTask,
			'getPlayersState': getPlayersState,
			'saveChallengeModel' : saveChallengeModel,
			'readChallengeModels' : readChallengeModels,
			'deleteChallengeModel' : deleteChallengeModel,
			'addIncrementalClassification' : addIncrementalClassification,
			'deleteIncrementalClassification' : deleteIncrementalClassification,
			'editIncrementalClassification' : editIncrementalClassification,
			'userProfile': userProfile,
			'setUrl': setUrl,
			 deleteChallenge,
			 updateChallenge,
			 playersSearch,
		};
	})
	.factory('utilsFactory', function () {
		// Utils factory
		// Get given instances length
		var getLength = function (game, type) {
			var len = 0;
			if (!!game) {
				if (type === 'points') {
					len = game.pointConcept.length;
				}
				if (type === 'badges_collections') {
					len = game.badgeCollectionConcept.length;
				}

			}
			return len;
		};

		return {
			'getLength': getLength,
		};
	});
