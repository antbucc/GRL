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

package eu.trentorise.game.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.base.MoreObjects;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.kie.api.definition.type.PropertyReactive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.trentorise.game.model.core.GameConcept;
import eu.trentorise.game.repo.LocalDateTimeDeserializer;
import eu.trentorise.game.repo.LocalDateTimeSerializer;

@PropertyReactive
public class PointConcept extends GameConcept {

	private Double score = 0.0;

	private Map<String, PeriodInternal> periods = new LinkedHashMap<String, PeriodInternal>();

	@JsonIgnore
	public static final DateTimeFormatter PERIOD_KEY_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

	@JsonIgnore
	long executionMoment = System.currentTimeMillis();

	public PointConcept(String name, long moment) {
		super(name);
		executionMoment = moment;
	}

	public PointConcept(String name) {
		super(name);
	}

	@JsonCreator
	public PointConcept(Map<String, Object> jsonProps) {
		super(jsonProps);
		if (jsonProps != null) {
			Object scoreField = jsonProps.get("score");
			// fix: in some case PointConcept JSON representation contains 0
			// value
			// in score field
			// and so it is cast to Integer
			if (scoreField != null) {
				if (scoreField instanceof Double) {
					score = (Double) scoreField;
				}
				if (scoreField instanceof Integer) {
					score = ((Integer) scoreField).doubleValue();
				}
			}

			Map<String, Object> temp = (Map<String, Object>) jsonProps.get("periods");
			if (temp != null) {
				Set<Entry<String, Object>> entries = temp.entrySet();
				for (Entry<String, Object> entry : entries) {
					periods.put(entry.getKey(), new PeriodInternal((Map<String, Object>) entry.getValue()));
				}
			}
		}
	}

	public Period getPeriod(String periodName) {
		return periods.get(periodName);
	}

	/*
	 * Actually I must have this methods to permit Jackson to correctly
	 * serialize the inner class
	 */
	public Map<String, PeriodInternal> getPeriods() {
		return periods;
	}

	/*
	 * Actually I must have this methods to permit Jackson to correctly
	 * serialize the inner class
	 */
	public void setPeriods(Map<String, PeriodInternal> periods) {
		this.periods = periods;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		increasePeriodicPoints(score - this.score);
		this.score = score;
	}

	public Double increment(Double score) {
		increasePeriodicPoints(score);
		this.score += score;
		return this.score;
	}

	private void increasePeriodicPoints(Double score) {
		for (PeriodInternal p : periods.values()) {
			p.increaseScore(score, executionMoment);
		}
	}

	public void addPeriod(String identifier, Date start, long period) {
		PeriodInternal p = new PeriodInternal(identifier, start, period);
		storePeriod(identifier, p);
	}

    private void storePeriod(String identifier, PeriodInternal period) {
        if (!periods.containsKey(identifier)) {
			periods.put(identifier, period);
		}
    }

    public void addPeriod(String identifier, Date start, Date end, long period) {
        PeriodInternal p = new PeriodInternal(identifier, start, end, period);
        storePeriod(identifier, p);
    }

	public void addPeriod(String identifier, Date start, long period, int capacity) {
		PeriodInternal p = new PeriodInternal(identifier, start, period, capacity);
		storePeriod(identifier, p);
	}

	public void deletePeriod(String identifier) {
		periods.remove(identifier);
	}

	public Double getPeriodCurrentScore(int periodIndex) {
		return new ArrayList<>(periods.values()).get(periodIndex).getCurrentScore();
	}

	public Double getPeriodCurrentScore(String periodIdentifier) {
		return periods.containsKey(periodIdentifier) ? periods.get(periodIdentifier).getCurrentScore() : 0d;
	}

	public PeriodInstance getPeriodCurrentInstance(int periodIndex) {
		return new ArrayList<>(periods.values()).get(periodIndex).getCurrentInstance();
	}

	public PeriodInstance getPeriodCurrentInstance(String periodIdentifier) {
		return periods.containsKey(periodIdentifier) ? periods.get(periodIdentifier).getCurrentInstance() : null;
	}

	public Double getPeriodPreviousScore(String periodIdentifier) {
		PeriodInstance previous = getPeriodPreviousInstance(periodIdentifier);
		return previous != null ? previous.getScore() : 0d;
	}

	public PeriodInstance getPeriodPreviousInstance(String periodIdentifier) {
		int currentIndex = getCurrentInstanceIndex(periodIdentifier);
		return currentIndex != -1 ? getPeriodInstance(periodIdentifier, currentIndex - 1) : null;
	}

	public Double getPeriodScore(String periodIdentifier, long moment) {
		return periods.containsKey(periodIdentifier) ? periods.get(periodIdentifier).getScore(moment) : 0d;
	}

	public PeriodInstance getPeriodInstance(String periodIdentifier, long moment) {
		return periods.containsKey(periodIdentifier) ? periods.get(periodIdentifier).retrieveInstance(moment) : null;
	}

	private int getCurrentInstanceIndex(String periodIdentifier) {
		PeriodInstance current = getPeriodCurrentInstance(periodIdentifier);
		return current != null ? current.getIndex() : -1;

	}

	public Double getPeriodScore(String periodIdentifier, int instanceIndex) {
		Double result = 0d;
		PeriodInternal p = periods.get(periodIdentifier);
		if (p != null) {
			PeriodInstance instance = getPeriodInstance(periodIdentifier, instanceIndex);
			result = instance != null ? instance.getScore() : 0d;
		}

		return result;
	}

	/**
	 * The method returns the PeriodInstance relative to the index.
	 * 
	 * @param periodIdentifier
	 *            identifier of period
	 * @param instanceIndex
	 *            index of instance
	 * @return PeriodInstance bound to the index or null if there is not
	 *         PeriodInstance at that index
	 */
	public PeriodInstance getPeriodInstance(String periodIdentifier, int instanceIndex) {
        PeriodInstance result = null;
        PeriodInternal p = periods.get(periodIdentifier);
        if (p != null) {
            if (p.period == -1 && instanceIndex > 0) {
                result = new PeriodInstanceImpl(p.getEnd().map(e -> e.getTime()).orElse(-1L), -1L);
            } else {
                LocalDateTime dateCursor = new LocalDateTime(p.start);
                dateCursor = dateCursor.withPeriodAdded(new org.joda.time.Period(p.period),
                        instanceIndex);
                result = getPeriodInstance(periodIdentifier, dateCursor.toDate().getTime());
            }
        }

        return result;
	}

	public interface Period {
		public Date getStart();

        public Optional<Date> getEnd();

		public long getPeriod();

		public String getIdentifier();

		public int getCapacity();
	}

	public interface PeriodInstance {
		public Double getScore();

		public long getStart();

		public long getEnd();

		public int getIndex();
	}

	private class PeriodInternal implements Period {
		private Date start;
        private Optional<Date> end;
		private long period;
		private String identifier;
		private int capacity;

		/*
		 * JsonDeserialize is used by convertValue method of ObjectMapper field
		 * of PlayerState. In constructor of playerState the StatePersistence
		 * object is deserialized.
		 * 
		 * convertValue method invoked PointConcept constructor annotated with
		 * JsonCreator.
		 * 
		 * Improve this flow. Use PointConceptTest.persistPeriod() as example
		 */
		@JsonDeserialize(keyUsing = LocalDateTimeDeserializer.class)
		@JsonSerialize(keyUsing = LocalDateTimeSerializer.class)
		private TreeMap<LocalDateTime, PeriodInstanceImpl> instances = new TreeMap<>();

		public PeriodInternal(String identifier, Date start, long period) {
			this.start = start;
            this.end = Optional.empty();
			this.period = period;
			this.identifier = identifier;
		}

		public PeriodInternal(String identifier, Date start, long period, int capacity) {
            this(identifier, start, period);
			this.capacity = capacity;
		}

        public PeriodInternal(String identifier, Date start, Date end, long period) {
            this.start = start;
            this.end = Optional.ofNullable(end);
            this.period = period;
            this.identifier = identifier;
        }

        public PeriodInternal(String identifier, Date start, Date end, long period, int capacity) {
            this(identifier, start, end, period);
            this.capacity = capacity;
        }

		public PeriodInternal(Map<String, Object> jsonProps) {
			if (jsonProps != null) {
				Object startField = jsonProps.get("start");
				if (startField != null) {
					start = new Date((long) startField);
				}

                Object endField = jsonProps.get("end");
                end = Optional.ofNullable(endField == null ? null : new Date((long) endField));

				Object periodField = jsonProps.get("period");
				if (periodField != null) {
					if (periodField instanceof Long) {
						period = (Long) periodField;
					}
					if (periodField instanceof Integer) {
						period = Integer.valueOf((Integer) periodField).longValue();
					}
				}
				identifier = (String) jsonProps.get("identifier");
				Object capacityField = jsonProps.get("capacity");
				if (capacityField != null) {
					capacity = (int) capacityField;
				}
				Map<String, Map<String, Object>> tempInstances = (Map<String, Map<String, Object>>) jsonProps
						.get("instances");
				if (tempInstances != null) {
					Set<Entry<String, Map<String, Object>>> entries = tempInstances.entrySet();
					for (Entry<String, Map<String, Object>> entry : entries) {
						instances.put(PERIOD_KEY_FORMAT.parseLocalDateTime(entry.getKey()),
								new PeriodInstanceImpl(entry.getValue()));
					}
				}
			}
		}

		private PeriodInstanceImpl getCurrentInstance() {
			return retrieveInstance(executionMoment);
		}

		@JsonIgnore
		public Double getCurrentScore() {
			try {
				return getCurrentInstance().getScore();
			} catch (IllegalArgumentException e) {
				return 0d;
			}
		}

		public Double getScore(long moment) {
			try {
				return retrieveInstance(moment).getScore();
			} catch (NullPointerException e) {
				return 0d;
			}
		}

		public Double increaseScore(Double value, long moment) {
			try {
				PeriodInstanceImpl instance = retrieveInstance(moment);
				return instance.increaseScore(value);
			} catch (IllegalArgumentException e) {
				return 0d;
			}
		}

        private PeriodInstanceImpl retrieveInstance(long moment) {
            LocalDateTime momentDate = new LocalDateTime(moment);
            if (start.after(momentDate.toDate())) {
                throw new IllegalArgumentException("moment is previous than startDate of period");
            }
            PeriodInstanceImpl instance = null;

            if (end.isPresent() && end.get().before(momentDate.toDate())) {
                instance = new PeriodInstanceImpl(end.get().getTime(), -1);
                instance.setIndex(-1);
            } else {
                LocalDateTime key = null;
                Interval interval = null;
                org.joda.time.Period jodaPeriod = null;
                long startAsMillis = start.getTime();
                long endAsMillis = -1;
                if (period > 0) {
                    LocalDateTime lowerBoundDate = instances.floorKey(momentDate);
                    if (lowerBoundDate == null) {
                        lowerBoundDate = new LocalDateTime(start.getTime());
                    }
                    jodaPeriod = new org.joda.time.Period(period);
                    boolean breakIteration = false;
                    do {
                        DateTime endInterval =
                                lowerBoundDate.withPeriodAdded(jodaPeriod, 1).toDateTime();
                        if (end.isPresent() && endInterval.isAfter(end.get().getTime())) {
                            endInterval = new DateTime(end.get().getTime());
                            breakIteration = true;
                        }
                        interval = new Interval(lowerBoundDate.toDateTime(), endInterval);
                        lowerBoundDate = interval.getEnd().toLocalDateTime();
                    } while (!interval.contains(moment) && !breakIteration);
                    startAsMillis = interval.getStartMillis();
                    endAsMillis = interval.getEndMillis();
                } else {
                    endAsMillis = end.map(e -> e.getTime()).orElse(-1L);
                }
                instance = instances.get(new LocalDateTime(startAsMillis));
                if (instance == null) {
                    instance = new PeriodInstanceImpl(startAsMillis, endAsMillis);
                    instance.setIndex(period > 0 ? getInstanceIndex(
                            new LocalDateTime(start.getTime()), jodaPeriod, momentDate) : 0);
                    key = new LocalDateTime(startAsMillis);
                    instances.put(key, instance);
                    if (capacity > 0 && instances.size() > capacity) {
                        instances.pollFirstEntry();
                    }
                }
            }
            return instance;
        }

		private int getInstanceIndex(LocalDateTime start, org.joda.time.Period period, LocalDateTime momentDate) {
			int index = -1;
			Interval interval = null;
			LocalDateTime cursorDate = start;
			DateTime moment = momentDate.toDateTime();
			do {
				interval = new Interval(cursorDate.toDateTime(), cursorDate.withPeriodAdded(period, 1).toDateTime());
				cursorDate = interval.getEnd().toLocalDateTime();
				index++;
			} while (!interval.contains(moment));

			return index;
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

		public TreeMap<LocalDateTime, PeriodInstanceImpl> getInstances() {
			return instances;
		}

		public void setInstances(TreeMap<LocalDateTime, PeriodInstanceImpl> instances) {
			this.instances = instances;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return identifier;
		}

		@Override
		public int getCapacity() {
			return capacity;
		}

		public int setCapacity(int capacity) {
			return this.capacity = capacity;
		}

        public Optional<Date> getEnd() {
            return end;
        }

        public void setEnd(Optional<Date> end) {
            this.end = end;
        }

	}

	private class PeriodInstanceImpl implements PeriodInstance {
		private Double score = 0d;
		private long start;
		private long end;
		private int index;

		public PeriodInstanceImpl(long start, long end) {
			this.start = start;
			this.end = end;
		}

		public PeriodInstanceImpl(Map<String, Object> jsonProps) {
			Object scoreField = jsonProps.get("score");
			Object startField = jsonProps.get("start");
			Object endField = jsonProps.get("end");
			Object indexField = jsonProps.get("index");
			if (scoreField != null) {
				if (scoreField instanceof Double) {
					score = (Double) scoreField;
				}

				if (scoreField instanceof Integer) {
					score = Integer.valueOf((Integer) scoreField).doubleValue();
				}
			}

			if (startField != null) {
				if (startField instanceof Long) {
					start = (Long) startField;
				}

				if (startField instanceof Integer) {
					start = Integer.valueOf((Integer) startField).longValue();
				}
			}

			if (endField != null) {
				if (endField instanceof Long) {
					end = (Long) endField;
				}

				if (endField instanceof Integer) {
					end = Integer.valueOf((Integer) endField).longValue();
				}
			}

			if (indexField != null) {
				if (indexField instanceof Integer) {
					index = Integer.valueOf((Integer) indexField);
				}
			}
		}

		public Double increaseScore(Double value) {
			score = score + value;
			return score;
		}

		public Double getScore() {
			return score;
		}

		public void setScore(Double score) {
			this.score = score;
		}

		public long getStart() {
			return start;
		}

		public void setStart(long start) {
			this.start = start;
		}

		public long getEnd() {
			return end;
		}

		public void setEnd(long end) {
			this.end = end;
		}

		@Override
		public String toString() {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return String.format("[start: %s, end: %s, score: %s, index: %s]", formatter.format(new Date(start)),
                    end > -1 ? formatter.format(new Date(end)) : "-", score, index);
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/*
	 * ExecutionMoment should be a private immutable field. Actually I need
	 * public getter/setter to expose the field to some tests with temporary
	 * constraints...TO IMPROVE
	 */
	public long getExecutionMoment() {
		return executionMoment;
	}

	/*
	 * ExecutionMoment should be a private immutable field. Actually I need
	 * public getter/setter to expose the field to some tests with temporary
	 * constraints...TO IMPROVE
	 */
	public void setExecutionMoment(long executionMoment) {
		this.executionMoment = executionMoment;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PointConcept) {
			PointConcept toCompare = (PointConcept) obj;
			return toCompare == this || name.equals(toCompare.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(11, 15).append(name).hashCode();
	}

	@Override
	public String toString() {
		return "***" + MoreObjects.toStringHelper(this)
				.add("name", name)
				.add("score", score)
				.omitNullValues() + "***";
	}
}
