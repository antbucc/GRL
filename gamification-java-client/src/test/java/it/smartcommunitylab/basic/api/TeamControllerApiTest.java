/**
 * Copyright 2018-2019 SmartCommunity Lab(FBK-ICT).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/*
 * Gamification Engine API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package it.smartcommunitylab.basic.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import it.smartcommunitylab.ApiClient;
import it.smartcommunitylab.ApiException;
import it.smartcommunitylab.auth.HttpBasicAuth;
import it.smartcommunitylab.model.ext.TeamDTO;

/**
 * API tests for TeamControllerApi
 */
@Ignore
public class TeamControllerApiTest {

    private final TeamControllerApi api = new TeamControllerApi();
    private ApiClient apiClient;
    private final String userName = "long-rovereto";
    private final String password = "rov";
    private String baseUrl = "http://localhost:6060/gamification";
    private String gameId = "5719e700e4b0bc2cc4677cb3";
    private String domain = "demo-domain";
    private String teamId = "1A";
    private String playerId = "1A";
    
    @Before
    public void init() {
    	 apiClient = new ApiClient(baseUrl);
    	
    	 // Configure OAuth2 access token for authorization: oauth2
    	 // OAuth oauth2 = (OAuth) apiClient.getAuthentication("oauth2");
    	 // oauth2.setAccessToken("YOUR_ACCESS_TOKEN");
    	 
    	 // Configure basic auth. 
    	 HttpBasicAuth basic = (HttpBasicAuth) apiClient.getAuthentication("basic");
    	 basic.setUsername(userName);
    	 basic.setPassword(password);

    	 api.setApiClient(apiClient);
    }
    
    
    /**
     * Add team member
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addTeamMemberUsingPUTTest() throws ApiException {
       
        List<String> members = new ArrayList<String>();
        members.add("M1");
        api.addTeamMemberUsingPUT(gameId, teamId, playerId, members);

        // TODO: test validations
    }
    
    /**
     * Create team
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createTeamUsingPOST1Test() throws ApiException {
        String gameId = null;
        TeamDTO team = null;
        api.createTeamUsingPOST1(gameId, team);

        // TODO: test validations
    }
    
    /**
     * Delte team
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteTeamUsingDELETE1Test() throws ApiException {
        String gameId = null;
        String teamId = null;
        api.deleteTeamUsingDELETE1(gameId, teamId);

        // TODO: test validations
    }
    
    /**
     * Get team members
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @Test
    public void readTeamMembersUsingGETTest() throws ApiException, JsonParseException, JsonMappingException, IOException {
        List<String> response = api.readTeamMembersUsingGET(gameId, teamId);

        System.out.println(response.size());
    }
    
    /**
     * Delete team member
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeTeamMemberUsingDELETETest() throws ApiException {
        String gameId = null;
        String teamId = null;
        String playerId = null;
        List<String> members = null;
        api.removeTeamMemberUsingDELETE(gameId, teamId, playerId, members);

        // TODO: test validations
    }
    
    /**
     * Edit team
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateTeamMembersUsingPUTTest() throws ApiException {
        String gameId = null;
        String teamId = null;
        List<String> members = null;
        api.updateTeamMembersUsingPUT(gameId, teamId, members);

        // TODO: test validations
    }
    
}
