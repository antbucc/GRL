package it.univaq.gamification.dsl.builders;


import it.univaq.gamification.dsl.PackageDescr;
import org.drools.compiler.lang.api.DescrBuilder;

public interface PackageDescrBuilder extends DescrBuilder<PackageDescrBuilder, PackageDescr> {

    PackageDescrBuilder name(String name);

    ImportDescrBuilder newImport(String target);

    GlobalDescrBuilder newGlobal(String type, String identifier);

    DeclareDescrBuilder newDeclare();

    RuleDescrBuilder newRule();

    PackageDescrBuilder end();

}
