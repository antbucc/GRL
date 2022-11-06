/**
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

package eu.trentorise.game.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.base.MoreObjects;
import eu.trentorise.game.model.core.ClassificationType;

public class Classification {
	private String name;
	private int position;
	private double score;
	private String scoreType;
	private ClassificationType classificationType;
	private int classificationRunNumber = -1;
	private PeriodReference periodReference;

	public Classification(String name, int position, String scoreType,
			ClassificationType classificationType) {
		this.name = name;
		this.position = position;
		this.scoreType = scoreType;
		this.classificationType = classificationType;
	}

	public Classification(String name, int position, String scoreType,
			ClassificationType classificationType, int instanceIndex,
			long startTimestamp, long endTimestamp, int executionTime) {
		this.name = name;
		this.position = position;
		this.scoreType = scoreType;
		this.classificationType = classificationType;
		this.classificationRunNumber = executionTime;
		periodReference = new PeriodReferenceImpl(instanceIndex,
				startTimestamp, endTimestamp);
	}

	public Classification() {
	}

	public interface PeriodReference {
		public long getStartTimestamp();

		public long getEndTimestamp();

		public String periodRepresentation();

		public int getInstanceIndex();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	private class PeriodReferenceImpl implements PeriodReference {
		private long startTimestamp;
		private long endTimestamp;
		private String periodRepresentation;
		private int instanceIndex;

		public PeriodReferenceImpl(int instanceIndex, long startTimestamp,
				long endTimestamp) {
			this.instanceIndex = instanceIndex;
			this.startTimestamp = startTimestamp;
			this.endTimestamp = endTimestamp;
			periodRepresentation = getRepresentation(startTimestamp,
					endTimestamp);

		}

		@Override
		public String toString() {
			return String
					.format("[instanceIndex: %s, start: %s, end: %s, periodRepresentation: %s]",
							instanceIndex, new Date(startTimestamp), new Date(
									endTimestamp), periodRepresentation);
		}

		private String getRepresentation(long start, long end) {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss");
			return String.format("%s-%s", formatter.format(new Date(start)),
					formatter.format(new Date(end)));
		}

		@Override
		public long getStartTimestamp() {
			return startTimestamp;
		}

		@Override
		public long getEndTimestamp() {
			return endTimestamp;
		}

		@Override
		public String periodRepresentation() {
			return periodRepresentation;
		}

		@Override
		public int getInstanceIndex() {
			return 0;
		}

	}

	@Override
	public String toString() {
		return "***" + MoreObjects.toStringHelper(this)
				.add("name", name)
				.add("classificationType", classificationType)
				.add("scoreType", scoreType)
				.add("score", score)
				.add("position", position)
				.add("executionTime",  classificationRunNumber)
				.add("periodReference", periodReference)
				.omitNullValues() + "***";
	}

	public ClassificationType getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(ClassificationType classificationType) {
		this.classificationType = classificationType;
	}

	public PeriodReference getPeriodReference() {
		return periodReference;
	}

	public void setPeriodReference(PeriodReference incrementalMetainfo) {
		this.periodReference = incrementalMetainfo;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getClassificationRunNumber() {
		return classificationRunNumber;
	}

	public void setClassificationRunNumber(int classificationRunNumber) {
		this.classificationRunNumber = classificationRunNumber;
	}
}
