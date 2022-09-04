package it.univaq.gamification.dsl.builders;

import it.univaq.gamification.dsl.builders.lhs.PackageDescr;
import org.drools.compiler.lang.api.*;

public interface DeclareDescrBuilder
        extends
        DescrBuilder<PackageDescrBuilder, PackageDescr> {

    TypeDeclarationDescrBuilder type();

}
