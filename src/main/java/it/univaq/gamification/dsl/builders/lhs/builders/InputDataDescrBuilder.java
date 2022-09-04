package it.univaq.gamification.dsl.builders.lhs.builders;

import it.univaq.gamification.dsl.builders.lhs.ConstraintType;
import it.univaq.gamification.dsl.binders.Bind;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface InputDataDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<InputDataDescrBuilder<P>> {

    <T> InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value);

    <T> InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value, Bind bind);


    InputDataDescrBuilder<P> bindFromData(Bind bind, String value);

}
