package it.univaq.gamification.dsl.builders.impl;


import it.univaq.gamification.dsl.CELambda;
import it.univaq.gamification.dsl.builders.GRLDescrBuilder;
import org.drools.model.view.ViewItemBuilder;

import java.util.ArrayList;
import java.util.Collections;

public class GRLDescrBuilderImpl<P> implements GRLDescrBuilder<P> {

    protected P parent;
    protected CELambda<ViewItemBuilder<?>> endFunction;
    protected ArrayList<ViewItemBuilder<?>> viewItemBuilders;

    protected GRLDescrBuilderImpl(P parent, CELambda<ViewItemBuilder<?>> endFunction) {
        this.parent = parent;
        this.endFunction = endFunction;
        this.viewItemBuilders = new ArrayList<>(Collections.emptyList());;
    }

    @Override
    public P end() {
        if (this.endFunction != null && this.viewItemBuilders.size() > 0) {
            this.endFunction.execute(this.viewItemBuilders);
        }

        return this.parent;
    }
}
