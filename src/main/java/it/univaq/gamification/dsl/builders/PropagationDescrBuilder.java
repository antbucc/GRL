package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PropagationDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<PropagationDescrBuilder<P>> {

    PropagationDescrBuilder<P> action(ConstraintType constraintType, String action);

    PropagationDescrBuilder<P> action(ConstraintType constraintType, String action, String bindName);

    PropagationDescrBuilder<P> bindAction(String bindName);

    PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level);

    PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level, String bindName);

    PropagationDescrBuilder<P> bindLevel(String bindName);

}
