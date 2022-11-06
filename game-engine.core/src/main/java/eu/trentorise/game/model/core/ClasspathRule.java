/**
 *    Copyright 2015 Fondazione Bruno Kessler - Trento RISE
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.trentorise.game.model.core;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathRule extends UrlRule {

	public static final String URL_PROTOCOL = "classpath://";

	public ClasspathRule(String gameId, String url) {
		super(gameId, url);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		try {
			return Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(getUrl().replace(URL_PROTOCOL, ""));
		} catch (NullPointerException e) {
			throw new IOException("null url");
		}
	}

}
