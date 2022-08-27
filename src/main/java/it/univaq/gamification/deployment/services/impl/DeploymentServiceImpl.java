package it.univaq.gamification.deployment.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.univaq.gamification.deployment.model.GameRule;
import it.univaq.gamification.deployment.services.DeploymentService;
import it.univaq.gamification.deployment.services.http.HttpClient;
import it.univaq.gamification.utils.DrlDumper;
import lombok.Builder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.compiler.lang.descr.RuleDescr;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

@Builder
public class DeploymentServiceImpl implements DeploymentService {

    private final Logger logger = LoggerFactory.getLogger(DeploymentServiceImpl.class);

    @Builder.Default
    private String protocol = "https";
    @Builder.Default
    private String domain = "localhost";
    @Builder.Default
    private Integer port = 8010;
    @Builder.Default
    private String username = "";
    @Builder.Default

    private String password = "";

    private final MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");

    @Builder
    public DeploymentServiceImpl(String protocol, String domain, Integer port, String username, String password) {
        this.protocol = protocol;
        this.domain = domain;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    private String getBaseUrl() {
        return String.format("%s://%s:%s/gamification/model/game", protocol, domain, port);
    }

    private String getBasicAuthHeaderValue() {
        return "Basic " + String.format(Base64.getEncoder().encodeToString(String.format("%s:%s", username, password).getBytes()));
    }

    private List<GameRule> getGameRules(String gameId) throws IOException {
        List<GameRule> gameRules = null;
        Request getGames = new Request.Builder()
                .header("Authorization", getBasicAuthHeaderValue())
                .url(String.format("%s/%s/rule", getBaseUrl(), gameId))
                .get()
                .build();

        logger.info("Retrieving rules of game with ID {}...", gameId);

        try (Response response = HttpClient.getInstance().newCall(getGames).execute()) {
            if (response.isSuccessful()) {
                logger.info("Rules of game {} retrieved", gameId);
                gameRules = Arrays.asList(new ObjectMapper().readValue(Objects.requireNonNull(response.body()).string(), GameRule[].class));
            } else {
                logger.error("Error while retrieving the rules:\n{}", Objects.requireNonNull(response.body()).string());
            }
        } catch (IOException e) {
            logger.error("An error occurred:\n{}", e.getMessage());
            throw e;
        }

        return gameRules;
    }

    private Map<String, String> getRuleNameIdMapping(String gameId) throws IOException {
        Map<String, String> mapping = new HashMap<>();

        for(GameRule gameRule : getGameRules(gameId)) {
            mapping.put(gameRule.getName(), gameRule.getId());
        }

        return mapping;
    }

    private void addRule(String gameId, String ruleName, String ruleContent) throws IOException {
        final String url = getBaseUrl() + String.format("/%s/rule", gameId);

        String payload = new JSONObject()
                .put("name", ruleName)
                .put("content", ruleContent)
                .toString();

        Request request = new Request.Builder()
                .header("Authorization", getBasicAuthHeaderValue())
                .url(url)
                .post(RequestBody.create(payload, jsonMediaType))
                .build();

        logger.info("Adding rule \"{}\" to game with ID {}...", ruleName, gameId);

        try (Response response = HttpClient.getInstance().newCall(request).execute()) {
            if (response.isSuccessful()) {
                logger.info("Rule \"{}\" added to game with ID {}", ruleName, gameId);
            } else {
                logger.error("Error while adding the rule:\n{}", Objects.requireNonNull(response.body()).string());
            }
        } catch (IOException e) {
            logger.error("An error occurred:\n{}", e.getMessage());
            throw e;
        }
    }

    private void editRule(String gameId, String ruleName, String ruleContent, String ruleId) throws IOException {
        final String url = getBaseUrl() + String.format("/%s/rule/%s", gameId, ruleId);

        String payload = new JSONObject()
                .put("id", ruleId)
                .put("name", ruleName)
                .put("content", ruleContent)
                .toString();

        Request request = new Request.Builder()
                .header("Authorization", getBasicAuthHeaderValue())
                .url(url)
                .put(RequestBody.create(payload, jsonMediaType))
                .build();

        logger.info("Editing rule \"{}\" (ID: {}) to game with ID {}...", ruleName, ruleId, gameId);

        try (Response response = HttpClient.getInstance().newCall(request).execute()) {
            if (response.isSuccessful()) {
                logger.info("Rule \"{}\" (ID: {}) edited at game with ID {}", ruleName, ruleId, gameId);
            } else {
                logger.error("Error while editing the rule: \n{}", Objects.requireNonNull(response.body()).string());
            }
        } catch (IOException e) {
            logger.error("An error occurred:\n{}", e.getMessage());
            throw e;
        }
    }

    private void deleteRule(String gameId, String ruleId) throws IOException {
        final String url = getBaseUrl() + String.format("/%s/rule/%s", gameId, ruleId);

        Request request = new Request.Builder()
                .header("Authorization", getBasicAuthHeaderValue())
                .url(url)
                .delete()
                .build();

        logger.info("Deleting rule with ID {} to game with ID {}...", ruleId, gameId);

        try (Response response = HttpClient.getInstance().newCall(request).execute()) {
            if (response.isSuccessful()) {
                logger.info("Rule with ID {} deleted at game with ID {}", ruleId, gameId);
            } else {
                logger.error("Error while deleting the rule: \n{}", Objects.requireNonNull(response.body()).string());
            }
        } catch (IOException e) {
            logger.error("An error occurred:\n{}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void upsert(String gameId, PackageDescr... packageDescrs) throws IOException {
        Map<String, String> ruleNameIdMapping = getRuleNameIdMapping(gameId);
        final DrlDumper drlDumper = new DrlDumper();

        for (PackageDescr packageDescr : packageDescrs) {
            // The gamification engine accepts a single rule as
            RuleDescr ruleDescr = packageDescr.getRules().get(0);
            String ruleId = ruleNameIdMapping.get(ruleDescr.getName());
            String ruleDRL = drlDumper.dump(packageDescr);

            if (ruleId == null) {
                addRule(gameId, ruleDescr.getName(), ruleDRL);
            } else {
                editRule(gameId, ruleDescr.getName(), ruleDRL, ruleId);
            }
        }
    }

    @Override
    public void delete(String gameId, PackageDescr... packageDescrs) throws IOException {
        Map<String, String> ruleNameIdMapping = getRuleNameIdMapping(gameId);

        for (PackageDescr packageDescr : packageDescrs) {
            // The gamification engine accepts a single rule as
            RuleDescr ruleDescr = packageDescr.getRules().get(0);
            String ruleId = ruleNameIdMapping.get(ruleDescr.getName());

            if (ruleId == null) {
                logger.warn("The rule with \"{}\" does not exist for game with ID {}", ruleDescr.getName(), gameId);
            } else {
                deleteRule(gameId, ruleId);
            }

        }
    }

}
