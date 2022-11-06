package eu.trentorise.game.core;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.trentorise.game.model.BadgeCollectionConcept;
import eu.trentorise.game.model.LevelInstance;
import eu.trentorise.game.model.PlayerState;
import eu.trentorise.game.model.PointConcept;
import eu.trentorise.game.task.Classification;

public class StatsLogger {

    private static final String LOGGER_NAME = "stats";

    private static final Logger statsLogger = LoggerFactory.getLogger(LOGGER_NAME);

    private static final Logger internalLogger = LoggerFactory.getLogger(StatsLogger.class);

    public static String logRule(String domain, String gameId, String playerId, String executionId,
            long executionMoment, long timestamp, String ruleName, PointConcept concept) {
        String msg = commonFieldsOutput(domain, gameId, playerId, executionId, executionMoment,
                timestamp);
        msg += " " + String.format("type=%s ruleName=\"%s\" name=\"%s\" score=%s",
                PointConcept.class.getSimpleName(), ruleName, concept.getName(),
                concept.getScore());
        statsLogger.info(msg);
        return msg;
    }

    public static String logRule(String domain, String gameId, String playerId, String executionId,
            long executionMoment, String ruleName, PointConcept concept) {
        return logRule(domain, gameId, playerId, executionId, executionMoment,
                System.currentTimeMillis(), ruleName, concept);
    }

    public static String logRulePointConceptDelta(String domain, String gameId, String playerId,
            String executionId, long executionMoment, String ruleName, PointConcept concept,
            double deltaScore) {
        return logRulePointConceptDelta(domain, gameId, playerId, executionId, executionMoment,
                System.currentTimeMillis(), ruleName, concept, deltaScore);
    }

    public static String logRuleBadgeCollectionConceptDelta(String domain, String gameId,
            String playerId, String executionId, long executionMoment, String ruleName,
            String conceptName, String badge) {
        return logRuleBadgeCollectionConceptDelta(domain, gameId, playerId, executionId,
                executionMoment, System.currentTimeMillis(), ruleName, conceptName, badge);
    }

    public static String logRule(String domain, String gameId, String playerId, String executionId,
            long executionMoment, long timestamp, String ruleName, BadgeCollectionConcept concept) {
        String msg = commonFieldsOutput(domain, gameId, playerId, executionId, executionMoment,
                timestamp);
        msg += " " + String.format("type=%s ruleName=\"%s\" name=\"%s\" badges=\"%s\"",
                BadgeCollectionConcept.class.getSimpleName(), ruleName, concept.getName(),
                concept.getBadgeEarned());
        statsLogger.info(msg);
        return msg;
    }

    public static String logRule(String domain, String gameId, String playerId, String executionId,
            long executionMoment, String ruleName, BadgeCollectionConcept concept) {
        return logRule(domain, gameId, playerId, executionId, executionMoment,
                System.currentTimeMillis(), ruleName, concept);
    }

    private static String logRulePointConceptDelta(String domain, String gameId, String playerId,
            String executionId, long executionMoment, long timestamp, String ruleName,
            PointConcept concept, double deltaScore) {
        String msg = commonFieldsOutput(domain, gameId, playerId, executionId, executionMoment,
                timestamp);

        msg += " " + String.format("type=%s ruleName=\"%s\" name=\"%s\" deltaScore=%s score=%s",
                PointConcept.class.getSimpleName(), ruleName, concept.getName(), deltaScore,
                concept.getScore());

        statsLogger.info(msg);
        return msg;
    }

    private static String logRuleBadgeCollectionConceptDelta(String domain, String gameId,
            String playerId, String executionId, long executionMoment, long timestamp,
            String ruleName, String conceptName, String badge) {
        String msg = commonFieldsOutput(domain, gameId, playerId, executionId, executionMoment,
                timestamp);

        msg += " " + String.format("type=%s ruleName=\"%s\" name=\"%s\" new_badge=\"%s\"",
                BadgeCollectionConcept.class.getSimpleName(), ruleName, conceptName, badge);
        statsLogger.info(msg);
        return msg;
    }

    public static String logAction(String domain, String gameId, String playerId,
            String executionId, long executionMoment, String action, Map<String, Object> inputData,
            List<Object> factObjects, PlayerState state) {
        return logAction(domain, gameId, playerId, executionId, executionMoment,
                System.currentTimeMillis(), action, inputData, factObjects, state);
    }

    public static String logAction(String domain, String gameId, String playerId,
            String executionId, long executionMoment, long timestamp, String action,
            Map<String, Object> inputData, List<Object> factObjects, PlayerState state) {
        String msg = commonFieldsOutput(domain, gameId, playerId, executionId, executionMoment,
                timestamp);

        msg += " " + String.format("type=%s actionName=\"%s\"", "Action", action);
        statsLogger.info(msg);
        return msg;
    }

    public static String logClassification(String domain, String gameId, String playerId,
            String executionId, long executionMoment, Map<String, Object> inputData,
            List<Object> factObjects) {
        return logClassification(domain, gameId, playerId, executionId, executionMoment,
                System.currentTimeMillis(), inputData, factObjects);
    }

    public static String logClassification(String domain, String gameId, String playerId,
            String executionId, long executionMoment, long timestamp, Map<String, Object> inputData,
            List<Object> factObjects) {
        String msg = commonFieldsOutput(domain, gameId, playerId, executionId, executionMoment,
                timestamp);

        if (CollectionUtils.isNotEmpty(factObjects)) {
            for (Object factObj : factObjects) {
                if (factObj instanceof Classification) {
                    Classification obj = (Classification) factObj;
                    msg += " " + String.format(
                            "type=%s classificationName=\"%s\" classificationPosition=%s",
                            "Classification", obj.getName(), obj.getPosition());
                    statsLogger.info(msg);
                    return msg;
                }
            }
        } else {
            LogHub.warn(gameId, internalLogger,
                    "Classification event with Classification payload null or absent in game {} for player {}",
                    gameId, playerId);
        }
        return "";
    }

    public static String logUserCreation(String domain, String gameId, String playerId,
            String executionId, long timestamp) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s creation", "UserCreation");
        statsLogger.info(msg);
        return msg;
    }

    public static String logBlacklist(String domain, String gameId, String playerId,
            String executionId, long timestamp, String blockedPlayerId) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s blockedPlayer=\"%s\"", "Blacklist", blockedPlayerId);
        statsLogger.info(msg);
        return msg;
    }

    public static String logUnblacklist(String domain, String gameId, String playerId,
            String executionId, long timestamp, String unblockedPlayerId) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " "
                + String.format("type=%s blockedPlayer=\"%s\"", "Unblacklist", unblockedPlayerId);
        statsLogger.info(msg);
        return msg;
    }

    public static String logChallengeAssignment(String domain, String gameId, String playerId,
            String executionId, long timestamp, String challengeName, Date start, Date end) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s name=\"%s\" startDate=%s endDate=%s",
                "ChallengeAssigned", challengeName, start != null ? start.getTime() : null,
                end != null ? end.getTime() : null);
        statsLogger.info(msg);
        return msg;
    }

    public static String logChallengeCompleted(String domain, String gameId, String playerId,
            String executionId, long executionTime, long timestamp, String challengeName) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s name=\"%s\" completed", "ChallengeCompleted",
                challengeName);
        statsLogger.info(msg);
        return msg;
    }


    public static String logChallengeProposed(String domain, String gameId, String playerId,
            String executionId, long executionTime, long timestamp, String challengeName) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " "
                + String.format("type=%s name=\"%s\" proposed", "ChallengeProposed", challengeName);
        statsLogger.info(msg);
        return msg;
    }

    public static String logChallengeAccepted(String domain, String gameId, String playerId,
            String executionId, long executionTime, long timestamp, String challengeName) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " "
                + String.format("type=%s name=\"%s\" accepted", "ChallengeAccepted", challengeName);
        statsLogger.info(msg);
        return msg;
    }


    public static String logChoiceActived(String domain, String gameId, String playerId,
            String executionId, long executionTime, long timestamp, String choice) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " "
                + String.format("type=%s choice=\"%s\"", "ChoiceActivated", choice);
        statsLogger.info(msg);
        return msg;
    }

    public static String logChallengeRefused(String domain, String gameId, String playerId,
            String executionId, long executionTime, long timestamp, String challengeName) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " "
                + String.format("type=%s name=\"%s\" refused", "ChallengeRefused", challengeName);
        statsLogger.info(msg);
        return msg;
    }


    public static String logChallengeFailed(String domain, String gameId, String playerId,
            String executionId, long executionTime, long timestamp, String challengeName) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, executionTime, timestamp);
        msg += " "
                + String.format("type=%s name=\"%s\" failed", "ChallengeFailed", challengeName);
        statsLogger.info(msg);
        return msg;
    }
    public static String logEndGameAction(String domain, String gameId, String playerId,
            String executionId, long executionTime, long timestamp) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s end", "EndGameAction");
        statsLogger.info(msg);
        return msg;
    }

    public static String logLevelGained(String domain, String gameId, String playerId,
            LevelInstance level, String executionId, long executionTime, long timestamp) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s levelName=\"%s\" levelType=\"%s\"", "LevelGained",
                level.getName(), level.getType());
        statsLogger.info(msg);
        return msg;
    }

    public static String logInviteToChallenge(String domain, String gameId, String playerId,
            String executionId, long executionTime, long timestamp, String guestId,
            String instanceName, String challengeType) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s guest=\"%s\" name=\"%s\" challengeType=\"%s\"",
                "ChallengeInvitation", guestId, instanceName, challengeType);
        statsLogger.info(msg);
        return msg;
    }

    public static String logChallengeInvitationAccepted(String domain, String gameId,
            String playerId, String executionId, long executionTime, long timestamp,
            String instanceName, String challengeType) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s name=\"%s\" challengeType=\"%s\"",
                "ChallengeInvitationAccepted", instanceName, challengeType);
        statsLogger.info(msg);
        return msg;
    }

    public static String logChallengeInvitationRefused(String domain, String gameId,
            String playerId, String executionId, long executionTime, long timestamp,
            String instanceName, String challengeType) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s name=\"%s\" challengeType=\"%s\"",
                "ChallengeInvitationRefused", instanceName, challengeType);
        statsLogger.info(msg);
        return msg;
    }

    public static String logChallengeInvitationCanceled(String domain, String gameId,
            String playerId, String executionId, long executionTime, long timestamp,
            String instanceName, String challengeType) {
        String msg =
                commonFieldsOutput(domain, gameId, playerId, executionId, timestamp, timestamp);
        msg += " " + String.format("type=%s name=\"%s\" challengeType=\"%s\"",
                "ChallengeInvitationCanceled", instanceName, challengeType);
        statsLogger.info(msg);
        return msg;
    }

    public static String logSurveyCompleted(String domain, String gameId, String playerId,
            String executionId, long executionMoment, Map<String, Object> inputData) {
        return logSurveyCompleted(domain, gameId, playerId, executionId, executionMoment,
                System.currentTimeMillis(), inputData);
    }

    public static String logSurveyCompleted(String domain, String gameId, String playerId,
            String executionId, long executionMoment, long timestamp,
            Map<String, Object> inputData) {
        String msg = commonFieldsOutput(domain, gameId, playerId, executionId, executionMoment,
                timestamp);
        final String surveyType = (String) inputData.getOrDefault("surveyType", "");
        msg += " " + String.format("type=%s surveyType=\"%s\"", "SurveyCompleted", surveyType);
        statsLogger.info(msg);
        return msg;
    }

    private static String commonFieldsOutput(String domain, String gameId, String playerId,
            String executionId, long executionMoment, long timestamp) {
        if(StringUtils.isBlank(domain)){
            return String.format("\"%s\" \"%s\" %s %s %s", gameId, playerId, executionId,
                    executionMoment, timestamp);   
        }else{
        return String.format("\"%s\" \"%s\" \"%s\" %s %s %s", domain, gameId, playerId, executionId,
                executionMoment, timestamp);
        }
    }
}
