package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface InputDataDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<InputDataDescrBuilder<P>> {

    <T> InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value);

    <T> InputDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value, String bindName);


    InputDataDescrBuilder<P> declareFromData(String bindName, String value);

}
