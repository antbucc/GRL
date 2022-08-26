package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.utils.BindName;
import it.univaq.gamification.dsl.utils.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PropagationDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<PropagationDescrBuilder<P>> {

    PropagationDescrBuilder<P> action(ConstraintType constraintType, String action);

    PropagationDescrBuilder<P> action(ConstraintType constraintType, String action, BindName bindName);

    PropagationDescrBuilder<P> bindAction(BindName bindName);

    PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level);

    PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level, BindName bindName);

    PropagationDescrBuilder<P> bindLevel(BindName bindName);

}
