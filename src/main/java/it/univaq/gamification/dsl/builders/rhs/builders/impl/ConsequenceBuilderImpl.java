package it.univaq.gamification.dsl.builders.rhs.builders.impl;

import it.univaq.gamification.dsl.Global;
import it.univaq.gamification.dsl.binders.BadgeCollectionBind;
import it.univaq.gamification.dsl.binders.Bind;
import it.univaq.gamification.dsl.binders.CustomDataBind;
import it.univaq.gamification.dsl.binders.PointBind;
import it.univaq.gamification.dsl.Operator;
import it.univaq.gamification.dsl.ValueHelper;
import it.univaq.gamification.dsl.builders.rhs.builders.ConsequenceBuilder;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.stream.Stream;


public class ConsequenceBuilderImpl<P> implements ConsequenceBuilder<P> {

    protected P parent;
    private final StringBuilder stringBuilder;
    private VelocityEngine velocityEngine;

    private final String ADD_BADGE_TEMPLATE = "/templates/addBadge.vm";
    private final String GAIN_LEVEL = "/templates/gainLevel.vm";
    private final String INCREASE_SCORE = "/templates/increaseScore.vm";
    private final String INSERT = "/templates/insert.vm";

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

    private <T> ConsequenceBuilder<P> addBadgeCreator(Bind badgeCollectionBind, T badge) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("badgeCollection", badgeCollectionBind.getValue());
        velocityContext.put("badge", ValueHelper.processValue(badge));

        this.addConsequence(ADD_BADGE_TEMPLATE, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> addBadge(BadgeCollectionBind badgeCollectionBind, String badge) {
        return this.addBadgeCreator(badgeCollectionBind, badge);
    }

    @Override
    public ConsequenceBuilder<P> addBadge(BadgeCollectionBind badgeCollectionBind, Bind badge) {
        return this.addBadgeCreator(badgeCollectionBind, badge);
    }

    private <T> ConsequenceBuilder<P> addBadgeWithNotificationCreator(Bind badgeCollectionBind, Bind gameIdBind, Bind teamIdBind, T badge) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("badgeCollection", badgeCollectionBind.getValue());
        velocityContext.put("gameId", gameIdBind.getValue());
        velocityContext.put("teamId", teamIdBind.getValue());
        velocityContext.put("badge", ValueHelper.processValue(badge));

        this.addConsequence(ADD_BADGE_TEMPLATE, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> addBadgeWithNotification(BadgeCollectionBind badgeCollectionBind, Bind gameIdBind, Bind teamIdBind, String badge) {
        return this.addBadgeWithNotificationCreator(badgeCollectionBind, gameIdBind, teamIdBind, badge);
    }

    @Override
    public ConsequenceBuilder<P> addBadgeWithNotification(BadgeCollectionBind badgeCollectionBind, Bind gameIdBind, Bind teamIdBind, Bind badge) {
        return this.addBadgeWithNotificationCreator(badgeCollectionBind, gameIdBind, teamIdBind, badge);
    }

    private <T> ConsequenceBuilder<P> gainLevelCreator(Bind errorScoreBind, Bind errorsBind, Bind customDataBind, T level) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("errorsScore", errorScoreBind.getValue());
        velocityContext.put("errors", errorsBind.getValue());
        velocityContext.put("customData", customDataBind.getValue());
        velocityContext.put("level", ValueHelper.processValue(level));

        this.addConsequence(GAIN_LEVEL, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> gainLevel(Bind errorScoreBind, Bind errorsBind, CustomDataBind customDataBind, String level) {
        return this.gainLevelCreator(errorScoreBind, errorsBind, customDataBind, level);
    }

    @Override
    public ConsequenceBuilder<P> gainLevel(Bind errorScoreBind, Bind errorsBind, CustomDataBind customDataBind, Bind level) {
        return this.gainLevelCreator(errorScoreBind, errorsBind, customDataBind, level);
    }

    private <T> ConsequenceBuilder<P> updateScore(Bind pointConceptBind, Operator operator, T amount) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("pc", pointConceptBind.getValue());
        velocityContext.put("operator", operator.getValue());
        velocityContext.put("amount", ValueHelper.processValue(amount));

        this.addConsequence(INCREASE_SCORE, velocityContext);

        return this;
    }

    @Override
    public ConsequenceBuilder<P> increaseScore(PointBind pointBind, Double amount) {
        return this.updateScore(pointBind, Operator.PLUS, amount);
    }

    @Override
    public ConsequenceBuilder<P> increaseScore(PointBind pointBind, Bind amount) {
        return this.updateScore(pointBind, Operator.PLUS, amount);
    }

    @Override
    public ConsequenceBuilder<P> increaseScore(PointBind pointBind, Global amount) {
        return this.updateScore(pointBind, Operator.PLUS, amount);
    }

    private ConsequenceBuilder<P> insert(VelocityContext velocityContext) {
        this.addConsequence(INSERT, velocityContext);
        return this;
    }

    @Override
    public ConsequenceBuilder<P> insert(Class<?> clazz) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("class", clazz.getSimpleName());

        return this.insert(velocityContext);
    }

    @Override
    public ConsequenceBuilder<P> insert(Class<?> clazz, Object... parameters) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("class", clazz.getSimpleName());
        velocityContext.put("parameters", Arrays.stream(parameters).map(ValueHelper::processValue).toArray());

        return this.insert(velocityContext);
    }

    @Override
    public P end() {
        return this.parent;
    }
}
