package it.univaq.gamification.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.drools.compiler.lang.MVELDumper;
import org.drools.compiler.lang.descr.PackageDescr;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.optimizers.OptimizerFactory;
import org.mvel2.templates.SimpleTemplateRegistry;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRegistry;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.res.Node;

public class DrlDumper  {

    protected final TemplateRegistry REPORT_REGISTRY = new SimpleTemplateRegistry();

    private final MVELDumper mvel = new MVELDumper();

    public DrlDumper() {
        OptimizerFactory.setDefaultOptimizer( "reflective" );

        REPORT_REGISTRY.addNamedTemplate( "drl",
                TemplateCompiler.compileTemplate(Objects.requireNonNull(DrlDumper.class.getResourceAsStream("/drl.mvel")),
                        (Map<String, Class< ? extends Node>>) null));

        TemplateRuntime.execute(REPORT_REGISTRY.getNamedTemplate("drl"), null, REPORT_REGISTRY);
    }

    public String dump(final PackageDescr pkg) {
        Map<String, Object> context = new HashMap<>();
        context.put("pkg", pkg);
        context.put("mvel",mvel);

        return (String) TemplateRuntime.execute(REPORT_REGISTRY.getNamedTemplate("drl"),
                null,
                new MapVariableResolverFactory(context),
                REPORT_REGISTRY
        );
    }

}