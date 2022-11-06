/**
 * Copyright 2015 Fondazione Bruno Kessler - Trento RISE
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package eu.trentorise.game.api.rest;

import static eu.trentorise.game.api.rest.ControllerUtils.decodePathVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.trentorise.game.bean.GameDTO;
import eu.trentorise.game.bean.GeneralClassificationDTO;
import eu.trentorise.game.bean.PlayerStateDTO;
import eu.trentorise.game.bean.RuleDTO;
import eu.trentorise.game.bean.TeamDTO;
import eu.trentorise.game.core.StatsLogger;
import eu.trentorise.game.model.BadgeCollectionConcept;
import eu.trentorise.game.model.Game;
import eu.trentorise.game.model.PlayerState;
import eu.trentorise.game.model.PointConcept;
import eu.trentorise.game.model.TeamState;
import eu.trentorise.game.model.core.DBRule;
import eu.trentorise.game.model.core.GameConcept;
import eu.trentorise.game.model.core.GameTask;
import eu.trentorise.game.service.IdentityLookupService;
import eu.trentorise.game.services.GameEngine;
import eu.trentorise.game.services.GameService;
import eu.trentorise.game.services.PlayerService;
import eu.trentorise.game.services.TaskService;
import eu.trentorise.game.task.GeneralClassificationTask;
import eu.trentorise.game.utils.Converter;

@RestController
@RequestMapping(value = "/console")
@Profile({"sec", "no-sec"})
public class ConsoleController {

    @Autowired
    private GameService gameSrv;

    @Autowired
    private TaskService taskSrv;

    @Autowired
    private GameEngine gameEngine;

    @Autowired
    private PlayerService playerSrv;

    @Autowired
    private Converter converter;

    @Autowired
    private IdentityLookupService identityLookup;

    @RequestMapping(method = RequestMethod.POST, value = "/game",
            consumes = {"application/json"}, produces = {"application/json"})
    public GameDTO saveGame(@RequestBody GameDTO game) {
        // set creator
        String user = identityLookup.getName();
        game.setOwner(user);
        Game res = gameSrv.saveGameDefinition(converter.convertGame(game));
        return converter.convertGame(res);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/game/{gameId}",
            produces = {"application/json"})
    public GameDTO readGame(@PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        Game g = gameSrv.loadGameDefinitionById(gameId);
        return g == null ? null : converter.convertGame(g);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/game/{gameId}",
            produces = {"application/json"})
    public void deleteGame(@PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        gameSrv.deleteGame(gameId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/game",
            produces = {"application/json"})
    public List<GameDTO> readGames() {
        String user = identityLookup.getName();
        List<GameDTO> r = new ArrayList<GameDTO>();
        for (Game g : gameSrv.loadGameByOwner(user)) {
            r.add(converter.convertGame(g));
        }
        return r;
    }

    /*
     * Maintain to permit platform version to work with old BASIC AUTH mode
     */
    @RequestMapping(method = RequestMethod.GET, value = "/game-by-domain",
            produces = {"application/json"})
    public List<GameDTO> readGamesByDomain() {
        return readGames();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/game/{gameId}/point",
            consumes = {"application/json"}, produces = {"application/json"})
    public void addPoint(@PathVariable String gameId,
            @RequestBody PointConcept point) {
        gameId = decodePathVariable(gameId);
        gameSrv.addConceptInstance(gameId, point);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/game/{gameId}/point",
            produces = {"application/json"})
    public List<PointConcept> readPoints(@PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        Set<GameConcept> concepts = gameSrv.readConceptInstances(gameId);
        List<PointConcept> points = new ArrayList<PointConcept>();
        if (concepts != null) {
            for (GameConcept gc : concepts) {
                if (gc instanceof PointConcept) {
                    points.add((PointConcept) gc);
                }
            }
        }

        return points;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/game/{gameId}/badgecoll",
            consumes = {"application/json"}, produces = {"application/json"})
    public void addBadge(@PathVariable String gameId,
            @RequestBody BadgeCollectionConcept badge) {
        gameId = decodePathVariable(gameId);
        gameSrv.addConceptInstance(gameId, badge);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/game/{gameId}/badgecoll",
            produces = {"application/json"})
    public List<BadgeCollectionConcept> readBadgeCollections(
            @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        Set<GameConcept> concepts = gameSrv.readConceptInstances(gameId);
        List<BadgeCollectionConcept> badgeColl = new ArrayList<BadgeCollectionConcept>();
        if (concepts != null) {
            for (GameConcept gc : concepts) {
                if (gc instanceof BadgeCollectionConcept) {
                    badgeColl.add((BadgeCollectionConcept) gc);
                }
            }
        }
        return badgeColl;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/game/{gameId}/rule/db",
            consumes = {"application/json"}, produces = {"application/json"})
    public RuleDTO addRule(@PathVariable String gameId,
            @RequestBody RuleDTO rule) {
        gameId = decodePathVariable(gameId);
        DBRule r = new DBRule(gameId, rule.getContent());
        r.setName(rule.getName());
        r.setId(rule.getId());
        String ruleUrl = gameSrv.addRule(r);
        rule.setId(ruleUrl);
        return rule;

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/game/{gameId}/rule/db/{ruleUrl}", produces = {"application/json"})
    public boolean deleteDbRule(@PathVariable String gameId,
            @PathVariable String ruleUrl) {
        gameId = decodePathVariable(gameId);
        ruleUrl = DBRule.URL_PROTOCOL + ruleUrl;
        return gameSrv.deleteRule(gameId, ruleUrl);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/game/{gameId}/rule/db/{ruleUrl}",
            produces = {"application/json"})
    public RuleDTO readDbRule(@PathVariable String gameId,
            @PathVariable String ruleUrl) {
        gameId = decodePathVariable(gameId);
        ruleUrl = DBRule.URL_PROTOCOL + ruleUrl;
        DBRule r = (DBRule) gameSrv.loadRule(gameId, ruleUrl);
        RuleDTO res = new RuleDTO();
        res.setId(r.getId());
        res.setName(r.getName());
        res.setContent(r.getContent());
        return res;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/game/{gameId}/task",
            consumes = {"application/json"}, produces = {"application/json"})
    public GeneralClassificationDTO addClassificationTask(
            @PathVariable String gameId, @RequestBody GeneralClassificationDTO task) {
        gameId = decodePathVariable(gameId);
        Game g = gameSrv.loadGameDefinitionById(gameId);
        if (g != null) {
            if (g.getTasks() == null) {
                g.setTasks(new HashSet<GameTask>());
            }
            GeneralClassificationTask t = converter.convertClassificationTask(task);
            t.setName(task.getName());
            if (g.getTasks().contains(t)) {
                throw new IllegalArgumentException("task name already exist");
            } else {
                g.getTasks().add(t);
                gameSrv.saveGameDefinition(g);
                taskSrv.createTask(t, gameId);
            }
            task.setGameId(gameId);
            return task;
        } else {
            throw new IllegalArgumentException("game not exist");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/game/{gameId}/task/del",
            consumes = {"application/json"}, produces = {"application/json"})
    public void deleteClassificationTask(@PathVariable String gameId,
            @RequestBody GeneralClassificationDTO task) {
        gameId = decodePathVariable(gameId);
        Game g = gameSrv.loadGameDefinitionById(gameId);
        if (g != null) {
            if (g.getTasks() != null) {
                GeneralClassificationTask t = converter.convertClassificationTask(task);
                t.setName(task.getName());
                g.getTasks().remove(t);
                gameSrv.saveGameDefinition(g);
                taskSrv.destroyTask(t, gameId);
            }
        } else {
            throw new IllegalArgumentException("game not exist");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/game/{gameId}/task",
            consumes = {"application/json"}, produces = {"application/json"})
    public void editClassificationTask(@PathVariable String gameId,
            @RequestBody GeneralClassificationDTO task) {
        gameId = decodePathVariable(gameId);
        Game g = gameSrv.loadGameDefinitionById(gameId);
        if (g != null) {
            if (g.getTasks() != null) {
                for (GameTask gt : g.getTasks()) {
                    if (gt instanceof GeneralClassificationTask
                            && gt.getName().equals(task.getName())) {
                        GeneralClassificationTask t = converter.convertClassificationTask(task);
                        GeneralClassificationTask ct = (GeneralClassificationTask) gt;
                        ct.setItemsToNotificate(t.getItemsToNotificate());
                        ct.setClassificationName(t.getClassificationName());
                        ct.setItemType(t.getItemType());
                        ct.setSchedule(t.getSchedule());
                        taskSrv.updateTask(gt, gameId);
                    }
                }
                gameSrv.saveGameDefinition(g);
            }
        } else {
            throw new IllegalArgumentException("game not exist");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/game/{gameId}/player",
            consumes = {"application/json"}, produces = {"application/json"})
    public void createPlayer(@PathVariable String gameId,
            @RequestBody PlayerStateDTO player) {
        gameId = decodePathVariable(gameId);
        // check if player already exists
        if (playerSrv.loadState(gameId, player.getPlayerId(), false, false) != null) {
            throw new IllegalArgumentException(String.format("Player %s already exists in game %s",
                    player.getPlayerId(), gameId));
        }

        player.setGameId(gameId);
        PlayerState p = converter.convertPlayerState(player);
        playerSrv.saveState(p);
        StatsLogger.logUserCreation(null, gameId, player.getPlayerId(),
                UUID.randomUUID().toString(), System.currentTimeMillis());
    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/game/{gameId}/player/{playerId}", produces = {"application/json"})
    public void deletePlayer(@PathVariable String gameId,
            @PathVariable String playerId) {
        gameId = decodePathVariable(gameId);
        playerId = decodePathVariable(playerId);
        playerSrv.deleteState(gameId, playerId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/game/{gameId}/player/{playerId}",
            consumes = {"application/json"}, produces = {"application/json"})
    public PlayerStateDTO updateCustomData(@PathVariable String gameId,
            @PathVariable String playerId, @RequestBody Map<String, Object> customData) {

        PlayerState state = playerSrv.loadState(gameId, playerId, false, false);

        if (state == null) {
            throw new IllegalArgumentException(
                    String.format("player %s doesn't exist in game %s", playerId, gameId));
        } else {
            state = playerSrv.updateCustomData(gameId, playerId, customData);
            return converter.convertPlayerState(state);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/game/{gameId}/team",
            consumes = {"application/json"}, produces = {"application/json"})
    public void createTeam(@PathVariable String gameId,
            @RequestBody TeamDTO team) {
        gameId = decodePathVariable(gameId);

        // check if player already exists
        if (playerSrv.readTeam(gameId, team.getPlayerId()) != null) {
            throw new IllegalArgumentException(
                    String.format("Team %s already exists in game %s", team.getPlayerId(), gameId));
        }

        team.setGameId(gameId);
        TeamState t = converter.convertTeam(team);
        playerSrv.saveTeam(t);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/game/{gameId}/team/{teamId}",
            produces = {"application/json"})
    public void deleteTeam(@PathVariable String gameId,
            @PathVariable String teamId) {
        gameId = decodePathVariable(gameId);
        teamId = decodePathVariable(teamId);
        deletePlayer(gameId, teamId);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/game/{gameId}/player/{playerId}/teams",
            produces = {"application/json"})
    public List<TeamDTO> readTeamsByMember(@PathVariable String gameId,
            @PathVariable String playerId) {
        gameId = decodePathVariable(gameId);
        playerId = decodePathVariable(playerId);
        List<TeamState> result = playerSrv.readTeams(gameId, playerId);
        List<TeamDTO> converted = new ArrayList<>();
        for (TeamState r : result) {
            converted.add(converter.convertTeam(r));
        }
        return converted;
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/game/{gameId}/team/{teamId}/members",
            consumes = {"application/json"}, produces = {"application/json"})
    public void updateTeamMembers(@PathVariable String gameId,
            @PathVariable String teamId, @RequestBody List<String> members) {

        gameId = decodePathVariable(gameId);
        teamId = decodePathVariable(teamId);
        TeamState team = playerSrv.readTeam(gameId, teamId);
        if (team != null) {
            team.setMembers(members);
            playerSrv.saveTeam(team);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rule/validate",
            consumes = {"application/json"}, produces = {"application/json"})
    public List<String> validateRule(@RequestBody String ruleContent) {
        return gameEngine.validateRule(null, ruleContent);
    }
}
