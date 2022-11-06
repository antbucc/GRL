/**
 * Copyright 2015 Fondazione Bruno Kessler - Trento RISE
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package eu.trentorise.game.service;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Profile({"sec", "platform"})
public class SpringSecurityIdentityLookup implements IdentityLookupService {

    @Override
    public String getName() {
        return getAuthentication().getName();
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getDomain() {
        return getAuthentication().getAuthorities().stream().findFirst().orElse(null)
                .getAuthority();
    }

}
