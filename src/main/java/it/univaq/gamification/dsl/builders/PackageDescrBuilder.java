package it.univaq.gamification.dsl.builders;


import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.PackageDescr;

public interface PackageDescrBuilder extends DescrBuilder<PackageDescrBuilder, PackageDescr> {

    public PackageDescrBuilder name( String name );

    public GlobalDescrBuilder newGlobal();

    public RuleDescrBuilder newRule();

    public PackageDescrBuilder end();

}
