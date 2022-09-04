package it.univaq.gamification.dsl.builders;


import it.univaq.gamification.dsl.builders.lhs.PackageDescr;
import it.univaq.gamification.dsl.Global;
import org.drools.compiler.lang.api.DescrBuilder;

public interface PackageDescrBuilder extends DescrBuilder<PackageDescrBuilder, PackageDescr> {

    PackageDescrBuilder name(String name);

    ImportDescrBuilder newImport(String target);

    GlobalDescrBuilder newGlobal(String type, Global global);

    DeclareDescrBuilder newDeclare();

    RuleDescrBuilder newRule();

    PackageDescrBuilder end();

}
