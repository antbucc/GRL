package it.univaq.gamification.dsl.builders.impl.rhs;

import it.univaq.gamification.dsl.builders.rhs.ConsequenceBuilder;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.StringWriter;


public class ConsequenceBuilderImpl<P> implements ConsequenceBuilder<P> {

    protected P parent;
    private StringBuilder stringBuilder;
    VelocityEngine velocityEngine;

    public ConsequenceBuilderImpl(P parent) {
        this.parent = parent;
        this.stringBuilder = new StringBuilder();
        this.initVelocity();
    }

    private void initVelocity() {
        this.velocityEngine = new VelocityEngine();
        this.velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADERS, "classpath");
        this.velocityEngine.setProperty("resource.loader.classpath.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        this.velocityEngine.init();
    }

    private void addConsequence(String templateName, VelocityContext velocityContext) {
        StringWriter stringWriter = new StringWriter();
        Template template = velocityEngine.getTemplate(templateName);
        template.merge(velocityContext, stringWriter);
        stringBuilder.append(stringWriter);
        stringBuilder.append("\n");
    }

    public StringBuilder getConsequence() {
        return this.stringBuilder;
    }

    @Override
    public ConsequenceBuilder<P> addBadge(String badgeCollectionRef, String badge) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("badgeCollection", badgeCollectionRef);
        velocityContext.put("badge", badge);

        this.addConsequence("templates/addBadge.vm", velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> addBadgeWithNotification(String badgeCollectionRef, String gameIdRef, String teamIdRef, String badge) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("badgeCollection", badgeCollectionRef);
        velocityContext.put("gameId", gameIdRef);
        velocityContext.put("teamId", teamIdRef);
        velocityContext.put("badge", badge);

        this.addConsequence("templates/addBadge.vm", velocityContext);

        return this;
    }

    @Override
    public P end() {
        return this.parent;
    }
}
