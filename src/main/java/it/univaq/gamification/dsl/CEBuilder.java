package it.univaq.gamification.dsl;

import it.univaq.gamification.dsl.ge.GamificationElementBuilder;

public interface CEBuilder<P> extends DescrBuilder<P>, GamificationElementBuilder<CEBuilder<P>> {

    CEBuilder<CEBuilder<P>> or();

    CEBuilder<CEBuilder<P>> and();

    CEBuilder<CEBuilder<P>> not();

    CEBuilder<CEBuilder<P>> exists();

}
