package it.univaq.gamification.dsl.builders;

import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.GlobalDescr;

public interface GlobalDescrBuilder extends DescrBuilder<PackageDescrBuilder, GlobalDescr> {
    GlobalDescrBuilder type(String var1);

    GlobalDescrBuilder identifier(String var1);
}

