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

package eu.trentorise.game.core;

import java.util.Date;

import eu.trentorise.game.model.core.TimeInterval;

public class TaskSchedule {
	private String cronExpression;
	private Date start;
	private long period;
	private TimeInterval delay;

	private CronFrequency frequency;

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public CronFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(CronFrequency frequency) {
		this.frequency = frequency;
	}

	public enum CronFrequency {
		DAILY("0 0 0 * * *"), WEEKLY("0 0 0 ? 0 *"), MONTHLY("0 0 0 1 * *");

		CronFrequency(String cronExpr) {
			this.cronExpr = cronExpr;
		}

		private final String cronExpr;

		public String getCronExpr() {
			return cronExpr;
		}

	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public TimeInterval getDelay() {
		return delay;
	}

	public void setDelay(TimeInterval delay) {
		this.delay = delay;
	}

}
