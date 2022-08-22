package it.univaq.gamification.dsl.builders;


import it.univaq.gamification.dsl.builders.lhs.CEDescrBuilder;
import it.univaq.gamification.dsl.builders.rhs.ConsequenceBuilder;
import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.AndDescr;
import org.drools.compiler.lang.descr.RuleDescr;

public interface RuleDescrBuilder extends DescrBuilder<PackageDescrBuilder, RuleDescr> {

    RuleDescrBuilder name(String name);

    CEDescrBuilder<RuleDescrBuilder, AndDescr> when();

    ConsequenceBuilder<RuleDescrBuilder> then();

}
