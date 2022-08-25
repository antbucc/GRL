package it.univaq.gamification.dsl.builders.impl.rhs;

import it.univaq.gamification.dsl.builders.rhs.ConsequenceBuilder;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.StringWriter;


public class ConsequenceBuilderImpl<P> implements ConsequenceBuilder<P> {

    protected P parent;
    private final StringBuilder stringBuilder;
    private VelocityEngine velocityEngine;

    private final String ADD_BADGE_TEMPLATE = "/templates/addBadge.vm";
    private final String LEVEL_ERROR = "/templates/levelError.vm";

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

        this.addConsequence(ADD_BADGE_TEMPLATE, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> addBadgeWithNotification(String badgeCollectionRef, String gameIdRef, String teamIdRef, String badge) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("badgeCollection", badgeCollectionRef);
        velocityContext.put("gameId", gameIdRef);
        velocityContext.put("teamId", teamIdRef);
        velocityContext.put("badge", badge);

        this.addConsequence(ADD_BADGE_TEMPLATE, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> levelError(String errorScoreRef, String errorsRef, String customDataRef, String level) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("errorsScore", errorScoreRef);
        velocityContext.put("errors", errorsRef);
        velocityContext.put("customData", customDataRef);
        velocityContext.put("level", level);

        this.addConsequence(LEVEL_ERROR, velocityContext);

        return this;
    }

    @Override
    public P end() {
        return this.parent;
    }
}
