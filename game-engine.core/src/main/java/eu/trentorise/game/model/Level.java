package eu.trentorise.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Level {
    private String name;
    private String pointConceptName;
    private List<Threshold> thresholds = new ArrayList<>();

    public Level() {}

	public Level(String name, String pointConceptName) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name cannot be blank");
        }

        if (StringUtils.isBlank(pointConceptName)) {
            throw new IllegalArgumentException("pointConceptName cannot be blank");
        }
        this.name = name;
        this.pointConceptName = pointConceptName;
    }


    public String getPointConceptName() {
        return pointConceptName;
    }


    public List<Threshold> getThresholds() {
        Collections.sort(thresholds, new Threshold.ValueComparator());
        return thresholds;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Level rhs = (Level) obj;
        return new EqualsBuilder().append(name, rhs.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(45, 13).append(name).hashCode();
    }


    @Override
    public String toString() {
        return String.format("{name=%s, pointConceptName=%s}", name, pointConceptName);
    }


    public String getName() {
        return name;
    }

    public static class Config {
        private int choices;
        private List<String> availableModels = new ArrayList<>();
        private List<String> activeModels = new ArrayList<>();

        public int getChoices() {
            return choices;
        }

        public void setChoices(int choices) {
            this.choices = choices;
        }

        public List<String> getAvailableModels() {
            return availableModels;
        }

        public List<String> getActiveModels() {
            return activeModels;
        }

        public void setActiveModels(List<String> activeModels) {
            this.activeModels = activeModels;
        }
    }

    public static class Threshold {
        private String name;
        private double value;
        private int index;
        private Config config;


        public Threshold() {}
        
        public Threshold(String name, double value) {
            this.name = name;
            this.value = value;
        }


        public Threshold updateValue(double newValue) {
            this.value = newValue;
            return this;
        }
        public String getName() {
            return name;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (obj == this) {
                return true;
            }

            if (obj.getClass() != getClass()) {
                return false;
            }

            Threshold rhs = (Threshold) obj;
            return new EqualsBuilder().append(name, rhs.name).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(43, 3).append(name).hashCode();
        }

        @Override
        public String toString() {
            return String.format("{name=%s, value=%s}", name, value);
        }


        public Config getConfig() {
            return config;
        }


        public void setConfig(Config config) {
            this.config = config;
        }

        static class ValueComparator implements Comparator<Threshold> {

            @Override
            public int compare(Threshold o1, Threshold o2) {
                return Double.compare(o1.getValue(), o2.getValue());
            }

        }

        public int getIndex() {
            return index;
        }


        public void setIndex(int index) {
            this.index = index;
        }
    }
}
