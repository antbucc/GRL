package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface PropagationDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<PropagationDescrBuilder<P>> {

    PropagationDescrBuilder<P> action(ConstraintType constraintType, String action);

    PropagationDescrBuilder<P> action(ConstraintType constraintType, String action, Bind bind);

    PropagationDescrBuilder<P> bindAction(Bind bind);

    PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level);

    PropagationDescrBuilder<P> level(ConstraintType constraintType, Integer level, Bind bind);

    PropagationDescrBuilder<P> bindLevel(Bind bind);

}
