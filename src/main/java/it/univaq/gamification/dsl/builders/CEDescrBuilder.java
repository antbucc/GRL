package it.univaq.gamification.dsl.builders;

import org.drools.compiler.lang.api.DescrBuilder;
import org.drools.compiler.lang.descr.*;

public interface CEDescrBuilder<P extends DescrBuilder<?, ?>, T extends AnnotatedBaseDescr> extends
        GamificationElementBuilder<CEDescrBuilder<P, T>, T>,
        DescrBuilder<P, T> {

    CEDescrBuilder<CEDescrBuilder<P, T>, AndDescr> and();

    CEDescrBuilder<CEDescrBuilder<P, T>, OrDescr> or();

    CEDescrBuilder<CEDescrBuilder<P, T>, NotDescr> not();

    CEDescrBuilder<CEDescrBuilder<P, T>, ExistsDescr> exists();

}
