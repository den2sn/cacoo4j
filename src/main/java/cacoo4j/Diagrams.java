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

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author den2sn
 */
public class Diagrams implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Diagram> result;
    private int count;

    /**
     * @return the result
     */
    public List<Diagram> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<Diagram> result) {
        this.result = result;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
}
