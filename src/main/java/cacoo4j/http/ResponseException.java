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
package cacoo4j.http;

/**
 *
 * @author den2sn
 */
public class ResponseException extends RuntimeException {

    private int responseCode;
    private String responseMessage;
    /**
     * Creates a new instance of
     * <code>CacooException</code> without detail message.
     */
    public ResponseException(int responseCode) {
        super(String.valueOf(responseCode));
        this.responseCode = responseCode;
        this.responseMessage = String.valueOf(responseCode);
    }

    /**
     * Constructs an instance of
     * <code>CacooException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ResponseException(int responseCode, String msg) {
        super(String.valueOf(responseCode) + " : " + msg);
        this.responseCode = responseCode;
        this.responseMessage = msg;
    }

    public ResponseException(int responseCode, String msg, Throwable t) {
        super(String.valueOf(responseCode) + " : " + msg, t);
        this.responseCode = responseCode;
        this.responseMessage = msg;
    }

    public ResponseException(int responseCode, Throwable t) {
        super(String.valueOf(responseCode), t);
        this.responseCode = responseCode;
        this.responseMessage = String.valueOf(responseCode);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
