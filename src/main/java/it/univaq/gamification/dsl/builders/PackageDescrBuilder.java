package it.univaq.gamification.dsl.builders;


import it.univaq.gamification.dsl.PackageDescr;
import org.drools.compiler.lang.api.DescrBuilder;

public interface PackageDescrBuilder extends DescrBuilder<PackageDescrBuilder, PackageDescr> {

    PackageDescrBuilder name(String name);

    PackageDescrBuilder newImport(String target);

    PackageDescrBuilder newGlobal(String type, String identifier);

    RuleDescrBuilder newRule();

    PackageDescrBuilder end();

}
