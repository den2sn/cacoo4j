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
public class Messages implements Serializable {
    private static final long serialVersionUID = 1L;

    private String diagramId;
    private List<Message> result;

    public String getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(String diagramId) {
        this.diagramId = diagramId;
    }

    public List<Message> getResult() {
        return result;
    }

    public void setResult(List<Message> result) {
        this.result = result;
    }
    
}
