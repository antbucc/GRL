package eu.trentorise.game.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GameStatistics {

	@Id
	private String id;
	private String gameId;
	private String pointConceptName;
	private String periodName;
	private Long startDate;
	private Long endDate;
	private String periodIndex; // key,
	private double average;
	private double variance;
	private Map<Integer, Double> quantiles;
	private Long lastUpdated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GameStatistics() {
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPointConceptName() {
		return pointConceptName;
	}

	public void setPointConceptName(String pointConceptName) {
		this.pointConceptName = pointConceptName;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public String getPeriodIndex() {
		return periodIndex;
	}

	public void setPeriodIndex(String periodIndex) {
		this.periodIndex = periodIndex;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public Map<Integer, Double> getQuantiles() {
		return quantiles;
	}

	public void setQuantiles(Map<Integer, Double> quantiles) {
		this.quantiles = quantiles;
	}

	public Long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
