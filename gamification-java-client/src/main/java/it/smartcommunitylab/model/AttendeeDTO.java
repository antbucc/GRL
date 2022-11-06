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


package it.smartcommunitylab.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * AttendeeDTO
 */

public class AttendeeDTO {
  @SerializedName("playerId")
  private String playerId = null;

  @SerializedName("role")
  private String role = null;

  public AttendeeDTO playerId(String playerId) {
    this.playerId = playerId;
    return this;
  }

   /**
   * Get playerId
   * @return playerId
  **/
  @ApiModelProperty(value = "")
  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public AttendeeDTO role(String role) {
    this.role = role;
    return this;
  }

   /**
   * Get role
   * @return role
  **/
  @ApiModelProperty(value = "")
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttendeeDTO attendeeDTO = (AttendeeDTO) o;
    return Objects.equals(this.playerId, attendeeDTO.playerId) &&
        Objects.equals(this.role, attendeeDTO.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, role);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttendeeDTO {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

