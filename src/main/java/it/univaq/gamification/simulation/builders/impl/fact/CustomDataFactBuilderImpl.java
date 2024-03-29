package it.univaq.gamification.simulation.builders.impl.fact;

import eu.trentorise.game.model.CustomData;
import it.univaq.gamification.simulation.builders.GameFactBuilder;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

public class CustomDataFactBuilderImpl implements GameFactBuilder<CustomDataFactBuilderImpl, CustomData> {
    @Builder.Default
    private Map<String, Object> data = new HashMap<>();
    private CustomData customData;

    @Builder
    public CustomDataFactBuilderImpl(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public CustomData asOriginalPojo() {
        if (this.customData == null) {
            this.customData = new CustomData();
            this.customData.putAll(this.data);
        }
        return this.customData;
    }

    public Map<String, Object> getData() {
        return this.asOriginalPojo();
    }

    public void setData(Map<String, Object> data) {
        this.asOriginalPojo().putAll(data);
    }

}
