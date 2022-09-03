package it.univaq.gamification.dsl.builders.impl.rhs;

import it.univaq.gamification.dsl.BindName;
import it.univaq.gamification.dsl.ConstraintHelper;
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
    private final String GAIN_LEVEL = "/templates/gainLevel.vm";

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

    public <T> String processValue(T value) {
        if (value instanceof BindName) {
            return ((BindName) value).getValue();
        }
        if (value.getClass().getName().equals(String.class.getName())) {
            return String.format("\"%s\"", value);
        }
        return value.toString();
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

    private <T> ConsequenceBuilder<P> addBadgeCreator(BindName badgeCollectionRef, T badge) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("badgeCollection", badgeCollectionRef.getValue());
        velocityContext.put("badge", processValue(badge));

        this.addConsequence(ADD_BADGE_TEMPLATE, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> addBadge(BindName badgeCollectionRef, String badge) {
        return this.addBadgeCreator(badgeCollectionRef, badge);
    }

    @Override
    public ConsequenceBuilder<P> addBadge(BindName badgeCollectionRef, BindName badge) {
        return this.addBadgeCreator(badgeCollectionRef, badge);
    }

    private <T> ConsequenceBuilder<P> addBadgeWithNotificationCreator(BindName badgeCollectionRef, BindName gameIdRef, BindName teamIdRef, T badge) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("badgeCollection", badgeCollectionRef.getValue());
        velocityContext.put("gameId", gameIdRef.getValue());
        velocityContext.put("teamId", teamIdRef.getValue());
        velocityContext.put("badge", processValue(badge));

        this.addConsequence(ADD_BADGE_TEMPLATE, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> addBadgeWithNotification(BindName badgeCollectionRef, BindName gameIdRef, BindName teamIdRef, String badge) {
        return this.addBadgeWithNotificationCreator(badgeCollectionRef, gameIdRef, teamIdRef, badge);
    }

    @Override
    public ConsequenceBuilder<P> addBadgeWithNotification(BindName badgeCollectionRef, BindName gameIdRef, BindName teamIdRef, BindName badge) {
        return this.addBadgeWithNotificationCreator(badgeCollectionRef, gameIdRef, teamIdRef, badge);
    }

    private <T> ConsequenceBuilder<P> gainLevelCreator(BindName errorScoreRef, BindName errorsRef, BindName customDataRef, T level) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("errorsScore", errorScoreRef.getValue());
        velocityContext.put("errors", errorsRef.getValue());
        velocityContext.put("customData", customDataRef.getValue());
        velocityContext.put("level", processValue(level));

        this.addConsequence(GAIN_LEVEL, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> gainLevel(BindName errorScoreRef, BindName errorsRef, BindName customDataRef, String level) {
        return this.gainLevelCreator(errorScoreRef, errorsRef, customDataRef, level);
    }

    @Override
    public ConsequenceBuilder<P> gainLevel(BindName errorScoreRef, BindName errorsRef, BindName customDataRef, BindName level) {
        return this.gainLevelCreator(errorScoreRef, errorsRef, customDataRef, level);
    }

    @Override
    public P end() {
        return this.parent;
    }
}
