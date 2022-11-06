package eu.trentorise.game.model.core;

import java.util.Date;
import java.util.Map;

/**
 * The class represents what a player collects in a game execution
 * 
 * 
 * @author mirko perillo
 * 
 */
public class PlayerMoveLog {

	private String id;
	private String gameId;
	private String playerId;
	private Date moment;
	private Map<String, Object> inputData;
	private Map<String, Double> scores;
	private Map<String, String[]> badges;

	public PlayerMoveLog(String gameId, String playerId, Date moment,
			Map<String, Object> inputData, Map<String, Double> scores,
			Map<String, String[]> badges) {
		this.gameId = gameId;
		this.playerId = playerId;
		this.moment = moment;
		this.inputData = inputData;
		this.scores = scores;
		this.badges = badges;
	}

	public String getGameId() {
		return gameId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public Date getMoment() {
		return moment;
	}

	public Map<String, Object> getInputData() {
		return inputData;
	}

	public Map<String, Double> getScores() {
		return scores;
	}

	public Map<String, String[]> getBadges() {
		return badges;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return String
				.format("{id:%s, gameId:%s, playerId:%s, moment:%s, inputData:%s, scores:%s, badges:%s}",
						id, gameId, playerId, moment, inputData, scores, badges);
	}
}
