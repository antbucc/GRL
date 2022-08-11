package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface CustomDataDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationElement<CustomDataDescrBuilder<P>> {

    CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, String value);

    CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, String value, String bindName);

    CustomDataDescrBuilder<P> declareFromData(String bindName, String value);

}
