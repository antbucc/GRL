package eu.trentorise.game.utils;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import eu.trentorise.game.api.rest.ChallengeController;
import eu.trentorise.game.managers.GameManager;
import eu.trentorise.game.model.ChallengeModel;
import eu.trentorise.game.model.Game;
import eu.trentorise.game.model.core.DBRule;
import eu.trentorise.game.model.core.GameTask;
import eu.trentorise.game.repo.ChallengeModelRepo;
import eu.trentorise.game.repo.GamePersistence;
import eu.trentorise.game.repo.GameRepo;
import eu.trentorise.game.services.TaskService;

@Component
@Transactional
@Repository
public class JsonDB {

	private static final Logger logger = LoggerFactory.getLogger(JsonDB.class); 
	
	@Autowired
	@Value("${import.dir}")
	private String importPath;

	@Autowired
	@Value("${export.dir}")
	private String exportPath;

	@Autowired
	private GameManager gameManager;

	@Autowired
	private GameRepo gameRepo;

	@Autowired
	private ChallengeModelRepo challengeModelRepo;
	
    @Autowired
    private TaskService taskSrv;

	ObjectMapper mapper = new ObjectMapper();

	/**
	 * Export DB GamePersistence, ChallengeModel, Rule
	 * 
	 * @throws Exception
	 */
	public void exportDB() throws Exception {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		List<GamePersistence> games = (List<GamePersistence>) gameRepo.findAll();
		Map<String, Map<String, Object>> gameJson = new HashMap<String, Map<String, Object>>();
		for (GamePersistence game : games) {
			Set<ChallengeModel> gameChallengeModels = challengeModelRepo.findByGameId(game.getId());
			Set<DBRule> rules = new HashSet<DBRule>();
			for (String rule : game.getRules()) {
				rules.add((DBRule) gameManager.loadRule(game.getId(), rule));
			}
			String gameId = game.getId();
			Map<String, Object> mapTemp = gameJson.get(gameId);
			if (mapTemp == null) {
				mapTemp = new HashMap<String, Object>();
				gameJson.put(gameId, mapTemp);
			}
			nullifyIds(game, rules, gameChallengeModels);
			mapTemp.put("game", game);
			mapTemp.put("rules", rules);
			mapTemp.put("challengeModels", gameChallengeModels);
		}
		File f = new File(exportPath, "db.json");
		logger.info("Export db file to path " + exportPath);
		mapper.writerWithDefaultPrettyPrinter().writeValue(f, gameJson);
	}

	private void nullifyIds(GamePersistence game, Set<DBRule> rules, Set<ChallengeModel> gameChallengeModels) {
		game.setId(null);
		game.setRules(null);
		for (ChallengeModel model : gameChallengeModels) {
			model.setId(null);
		}
		for (DBRule r : rules) {
			r.setGameId(null);
			r.setId(null);
		}

	}

	/**
	 * Import JSON DB GamePersistence, ChallengeModel, Rule
	 * 
	 * @throws Exception
	 */
	public void importDB() throws Exception {
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			File f = new File(importPath, "db.json");
			Map<?, ?> map = mapper.readValue(f, Map.class);
			logger.info("Import db from file path " + exportPath);
			map.entrySet().forEach(entry -> {
				Map<String, Object> gameData = (Map<String, Object>) entry.getValue();
				GamePersistence g = mapper.convertValue(gameData.get("game"), GamePersistence.class);
				List<?> r = (List<?>) gameData.get("rules");
				List<?> chModels = (List<?>) gameData.get("challengeModels");
				persist(g, r, chModels);
			});
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void persist(GamePersistence g, List<?> rules, List<?> chModels) {
		GamePersistence pg = gameRepo.save(g);
		Game game = gameManager.loadGameDefinitionById(g.getId());
		if (game != null) {
			for (GameTask task : game.getTasks()) {
				taskSrv.createTask(task, game.getId());
			}
		}
		for (Object cmObj : chModels) {
			ChallengeModel cm = mapper.convertValue(cmObj, ChallengeModel.class);
			cm.setGameId(pg.getId());
			gameManager.saveChallengeModel(pg.getId(), cm);
		}
		for (Object rObj : rules) {
			DBRule r = mapper.convertValue(rObj, DBRule.class);
			r.setGameId(pg.getId());
			gameManager.addRule(r);
		}
	}

}
