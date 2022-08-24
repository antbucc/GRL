package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.model.InputData;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.Builder;

import java.util.Map;

public class InputDataFactBuilderImpl implements GameFactBuilder<InputDataFactBuilderImpl, InputData> {
    private final Map<String, Object> data;
    private InputData inputData;

    @Builder
    public InputDataFactBuilderImpl(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public InputData asOriginalPojo() {
        if (this.inputData == null) {
            this.inputData = new InputData(this.data);
        }
        return this.inputData;
    }

    public Map<String, Object> getData() {
        return this.asOriginalPojo().getData();
    }

    public void setData() {
        this.asOriginalPojo().setData(data);
    }
}
