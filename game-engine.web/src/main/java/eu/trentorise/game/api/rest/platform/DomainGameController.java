package eu.trentorise.game.api.rest.platform;

import static eu.trentorise.game.api.rest.ControllerUtils.decodePathVariable;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.trentorise.game.api.rest.GameController;
import eu.trentorise.game.bean.GameDTO;
import eu.trentorise.game.bean.LevelDTO;
import eu.trentorise.game.core.LogHub;
import eu.trentorise.game.model.Game;
import eu.trentorise.game.model.GameStatistics;
import eu.trentorise.game.model.Level;
import eu.trentorise.game.service.IdentityLookupService;
import eu.trentorise.game.services.GameService;
import eu.trentorise.game.utils.Converter;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@Profile("platform")
public class DomainGameController  {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameSrv;

    @Autowired
    private Converter converter;

    @Autowired
    private IdentityLookupService identityLookup;

    /**
     * ************************************************************************* GAME API
     * ************************************************************************
     */

    // Create a game
    // POST /model/game
    // TODO: ­ Remove from body implicit elements (e.g., owner) , The content
    // elements are optional (except name)

    @RequestMapping(method = RequestMethod.POST, value = "/api/{domain}/model/game",
            consumes = {"application/json"}, produces = {"application/json"})
    @Operation(summary = "Save a game")
    public GameDTO saveGame(@PathVariable String domain, @RequestBody GameDTO game) {
        // set creator
        String user = identityLookup.getName();
        game.setOwner(user);
        game.setDomain(domain);
        Game res = gameSrv.saveGameDefinition(converter.convertGame(game));
        return converter.convertGame(res);
    }

    //
    // Read a game
    // GET /model/game/{id}

    @RequestMapping(method = RequestMethod.GET, value = "/api/{domain}/model/game/{gameId}",
            produces = {"application/json"})
    @Operation(summary = "Read game definition")
    public GameDTO readGame(@PathVariable String domain, @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        Game g = gameSrv.loadGameDefinitionById(gameId);
        return g == null ? null : converter.convertGame(g);
    }

    // Read all games of the user
    // GET /model/game

    @RequestMapping(method = RequestMethod.GET, value = "/api/{domain}/model/game",
            produces = {"application/json"})
    @Operation(summary = "Get games", description = "Get all the game definitions of a user")
    public List<GameDTO> readGames(@PathVariable String domain) {
        String user = identityLookup.getName();
        List<GameDTO> r = new ArrayList<GameDTO>();
        for (Game g : gameSrv.loadGameByOwner(user)) {
            r.add(converter.convertGame(g));
        }
        return r;
    }

    // Delete a game
    // DELETE /model/game/{id}

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/{domain}/model/game/{gameId}",
            produces = {"application/json"})
    @Operation(summary = "Delete game")
    public void deleteGame(@PathVariable String domain, @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        gameSrv.deleteGame(gameId);
    }

    // Start a game
    // PUT /model/game/{id}/start

    @RequestMapping(method = RequestMethod.PUT, value = "/api/{domain}/model/game/{gameId}/start",
            consumes = {"application/json"}, produces = {"application/json"})
    @Operation(summary = "Start game", description = "The game is able to accept action executions")
    public void startGame(@PathVariable String domain, @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        throw new UnsupportedOperationException("Operation actually not supported");
    }

    // Stop a game
    // PUT /model/game/{id}/stop

    @RequestMapping(method = RequestMethod.PUT, value = "/api/{domain}/model/game/{gameId}/stop",
            consumes = {"application/json"}, produces = {"application/json"})
    @Operation(summary = "Stop a game",
            description = "The game will not accept action execution anymore")
    public void stopGame(@PathVariable String domain, @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        throw new UnsupportedOperationException("Operation actually not supported");
    }

    /**
     * ************************************************************************* ACTION API
     * ************************************************************************
     */

    // Create action concept
    // POST /model/game/{id}/action/{actionId}
    // ­ Action should be unique. Error if exists
    // ­ Consider other fields: name, description

    @RequestMapping(method = RequestMethod.POST,
            value = "/api/{domain}/model/game/{gameId}/action/{actionId}",
            consumes = {"application/json"}, produces = {"application/json"})
    @Operation(summary = "Add action")
    public void addAction(@PathVariable String domain, @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        throw new UnsupportedOperationException("Operation actually not supported");
    }

    // Modify an action
    // PUT /model/game/{id}/action/{actionId}

    @RequestMapping(method = RequestMethod.PUT,
            value = "/api/{domain}/model/game/{domain}/action/{actionId}",
            consumes = {"application/json"}, produces = {"application/json"})
    @Operation(summary = "Edit action")
    public void editAction(@PathVariable String domain, @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        throw new UnsupportedOperationException("Operation actually not supported");
    }

    // Read all actions
    // GET /model/game/{id}/action

    @RequestMapping(method = RequestMethod.GET, value = "/api/{domain}/model/game/{gameId}/action",
            produces = {"application/json"})
    @Operation(summary = "Get actions")
    public Set<String> readAllAction(@PathVariable String domain, @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        Game g = gameSrv.loadGameDefinitionById(gameId);
        return g != null ? g.getActions() : Collections.<String>emptySet();

    }

    // Read an action
    // GET /model/game/{id}/action/{actionId}

    @RequestMapping(method = RequestMethod.GET,
            value = "/api/{domain}/model/game/{gameId}/action/{actionId}",
            produces = {"application/json"})
    @Operation(summary = "Get action")
    public void readAction(@PathVariable String domain, @PathVariable String gameId) {
        gameId = decodePathVariable(gameId);
        throw new UnsupportedOperationException("Operation actually not supported");
    }

    // Delete an action
    // DELETE /model/game/{id}/action/{actionId}
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/api/{domain}/model/game/{gameId}/action/{actionId}",
            produces = {"application/json"})
    @Operation(summary = "Delete action")
    public void deleteAction(@PathVariable String domain, @PathVariable String gameId,
            @PathVariable String actionId) {
        gameId = decodePathVariable(gameId);
        actionId = decodePathVariable(actionId);
        Game g = gameSrv.loadGameDefinitionById(gameId);

        if (g != null) {
            g.getActions().remove(actionId);
            gameSrv.saveGameDefinition(g);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/{domain}/model/game/{gameId}/level",
            produces = {"application/json"})
    @Operation(summary = "Save a level")
    public LevelDTO saveLevel(@PathVariable String gameId, @RequestBody LevelDTO level) {
        Game game = gameSrv.upsertLevel(gameId, converter.convert(level));

        Level saved = game.getLevels().stream().filter(lev -> lev.getName().equals(level.getName()))
                .findFirst().orElse(null);
        return converter.convert(saved);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/api/{domain}/model/game/{gameId}/level/{levelName}",
            produces = {"application/json"})
    @Operation(summary = "Delete a level")
    public boolean deleteLevel(@PathVariable String gameId, @PathVariable String levelName) {
        gameSrv.deleteLevel(gameId, levelName);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/api/{domain}/data/game/{gameId}/statistics",
            produces = {"application/json"})
    @Operation(summary = "Get game statistics")
    public List<GameStatistics> readGameStatistics(@PathVariable String domain,
            @PathVariable String gameId,
            @RequestParam(required = false) String pointConceptName,
            @RequestParam(required = false) String periodName,
            @RequestParam(required = false) Long timestamp,
            @RequestParam(required = false) String periodIndex,
            @RequestParam(required = false, defaultValue = "-1") int page,
            @RequestParam(required = false, defaultValue = "-1") int size) throws Exception {

        gameId = URLDecoder.decode(gameId, "UTF-8");

        if (pointConceptName != null) {
            pointConceptName = decodePathVariable(pointConceptName);
        }
        if (periodName != null) {
            periodName = decodePathVariable(periodName);
        }
        if (periodIndex != null) {
            periodIndex = decodePathVariable(periodIndex);
        }
        // put this to maintain same behavior of pageable config (start page
        // from index 1)
        if (page == 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        LogHub.info(gameId, logger,
                String.format(
                        "read statistics for game [%s], pointConceptName [%s], period [%s], timestamp [%s], periodIndex[%s]",
                        gameId, pointConceptName, periodName, timestamp, periodIndex));

        return gameSrv.loadGameStats(gameId, pointConceptName, periodName, timestamp, periodIndex,
                createPageRequest(page, size));

    }

    private PageRequest createPageRequest(int page, int size) {
        PageRequest pageRequest = null;
        if (page != -1 && size != -1) {
            // put page-1 to maintain same behavior of pageable config ( start
            // page
            // from index 1)
            pageRequest = PageRequest.of(page - 1, size);
        }
        return pageRequest;
    }

}
