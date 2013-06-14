/*
 * Copyright 2013 den2sn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cacoo4j.oauth;

import java.net.HttpURLConnection;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

/**
 *
 * @author den2sn
 */
public class OAuthUtil {
    OAuthConsumer consumer;
    OAuthProvider provider;
    
    public OAuthUtil(String key, String secret) {
        provider = new DefaultOAuthProvider(
                "https://cacoo.com/oauth/request_token",
                "https://cacoo.com/oauth/access_token",
                "https://cacoo.com/oauth/authorize");
        consumer = new DefaultOAuthConsumer(key, secret);
    }

    public String retrieveRequestToken() {
        try {
            return provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public AccessToken getToken(String pin) {
        try {
            provider.retrieveAccessToken(consumer, pin);
            AccessToken token = new AccessToken();
            token.setToken(consumer.getToken());
            token.setTokenSecret(consumer.getTokenSecret());
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sign(AccessToken token, HttpURLConnection connection) {
        try {
            consumer.setTokenWithSecret(token.getToken(), token.getTokenSecret());
            consumer.sign(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
