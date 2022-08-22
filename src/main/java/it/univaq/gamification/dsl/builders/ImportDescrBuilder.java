package it.univaq.gamification.dsl.builders;


import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.ImportDescr;

public interface ImportDescrBuilder
        extends
        DescrBuilder<PackageDescrBuilder, ImportDescr> {

    ImportDescrBuilder target(String target);

}

