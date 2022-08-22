package it.univaq.gamification.dsl.builders;


import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PackageDescr;

public interface PackageDescrBuilder extends DescrBuilder<PackageDescrBuilder, PackageDescr> {

    PackageDescrBuilder name(String name);

    PackageDescrBuilder newImport(String target);

    PackageDescrBuilder newGlobal(String type, String identifier);

    RuleDescrBuilder newRule();

    PackageDescrBuilder end();

}
