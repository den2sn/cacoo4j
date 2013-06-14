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
package cacoo4j;

/**
 *
 * @author den2sn
 */
public class LimitException extends RuntimeException {

    /**
     * Creates a new instance of
     * <code>CacooException</code> without detail message.
     */
    public LimitException() {
    }

    /**
     * Constructs an instance of
     * <code>CacooException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LimitException(String msg) {
        super(msg);
    }

    public LimitException(String msg, Throwable t) {
        super(msg, t);
    }

    public LimitException(Throwable t) {
        super(t);
    }
}
