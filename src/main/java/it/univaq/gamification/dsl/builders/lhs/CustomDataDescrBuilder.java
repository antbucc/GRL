package it.univaq.gamification.dsl.builders.lhs;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintType;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PatternDescr;

public interface CustomDataDescrBuilder<P extends DescrBuilder<?, ?>>
        extends DescrBuilder<P, PatternDescr>, GamificationBaseDescrBuilder<CustomDataDescrBuilder<P>> {

    <T> CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value);

    <T> CustomDataDescrBuilder<P> fromData(ConstraintType constraintType, String key, T value, BindName bindName);

    CustomDataDescrBuilder<P> bindFromData(BindName bindName, String value);

}
