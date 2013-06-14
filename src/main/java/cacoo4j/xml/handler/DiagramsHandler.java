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
package cacoo4j.xml.handler;

import cacoo4j.CacooUser;
import cacoo4j.Diagram;
import cacoo4j.Diagrams;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author den2sn
 */
public class DiagramsHandler extends AbstructHandler {
    private Diagrams diagrams;
    private List<Diagram> list;
    private Diagram diagram;
    private CacooUser owner;
    private StringBuilder sb;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {
        if ("diagrams".equals(qName)) {
            diagrams = new Diagrams();
        } else if ("result".equals(qName)) {
            list = new ArrayList<Diagram>();
        } else if("diagram".equals(qName)) {
            diagram = new Diagram();
        } else if("owner".equals(qName)) {
            owner = new CacooUser();
        } else if("url".equals(qName) ||
                  "imageUrl".equals(qName) ||
                  "imageUrlForApi".equals(qName) ||
                  "diagramId".equals(qName) ||
                  "title".equals(qName) ||
                  "description".equals(qName) ||
                  "security".equals(qName) ||
                  "type".equals(qName) ||
                  "name".equals(qName) ||
                  "nickname".equals(qName) ||
                  "editing".equals(qName) ||
                  "own".equals(qName) ||
                  "shared".equals(qName) ||
                  "folderId".equals(qName) ||
                  "folderName".equals(qName) ||
                  "sheetCount".equals(qName) ||
                  "created".equals(qName) ||
                  "updated".equals(qName) ||
                  "count".equals(qName)) {
            sb = new StringBuilder();
        }
    }

    @Override
    public void endElement(String namespaceURI,String localName,String qName) throws SAXException {
        if ("result".equals(qName)) {
            diagrams.setResult(list);
            list = null;
        } else if("diagram".equals(qName)) {
            list.add(diagram);
            diagram = null;
        } else if("owner".equals(qName)) {
            diagram.setOwner(owner);
            owner = null;
        } else if("url".equals(qName)) {
            diagram.setUrl(sb.toString());
            sb = null;
        } else if("imageUrl".equals(qName)) {
            if (owner != null) {
                owner.setImageUrl(sb.toString());
            } else {
                diagram.setImageUrl(sb.toString());
            }
            sb = null;
        } else if("imageUrlForApi".equals(qName)) {
            diagram.setImageUrlForApi(sb.toString());
            sb = null;
        } else if("diagramId".equals(qName)) {
            diagram.setDiagramId(sb.toString());
            sb = null;
        } else if("title".equals(qName)) {
            diagram.setTitle(sb.toString());
            sb = null;
        } else if("description".equals(qName)) {
            diagram.setDescription(sb.toString());
            sb = null;
        } else if("security".equals(qName)) {
            diagram.setSecurity(sb.toString());
            sb = null;
        } else if("type".equals(qName)) {
            if (owner != null) {
                owner.setType(sb.toString());
            } else {
                diagram.setType(sb.toString());
            }
            sb = null;
        } else if("name".equals(qName)) {
            if (owner != null) {
                owner.setName(sb.toString());
            }
            sb = null;
        } else if("nickname".equals(qName)) {
            if (owner != null) {
                owner.setNickname(sb.toString());
            }
            sb = null;
        } else if("editing".equals(qName)) {
            diagram.setEditing(Boolean.parseBoolean(sb.toString()));
            sb = null;
        } else if("own".equals(qName)) {
            diagram.setOwn(Boolean.parseBoolean(sb.toString()));
            sb = null;
        } else if("shared".equals(qName)) {
            diagram.setShared(Boolean.parseBoolean(sb.toString()));
            sb = null;
        } else if("folderId".equals(qName)) {
            diagram.setFolderId(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("folderName".equals(qName)) {
            diagram.setFolderName(sb.toString());
            sb = null;
        } else if("sheetCount".equals(qName)) {
            diagram.setSheetCount(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("created".equals(qName)) {
            try {
                diagram.setCreated(format.parse(sb.toString()));
            } catch(Exception e) {
            }
            sb = null;
        } else if("updated".equals(qName)) {
            try {
                diagram.setUpdated(format.parse(sb.toString()));
            } catch(Exception e) {
            }
            sb = null;
        } else if("count".equals(qName)) {
            diagrams.setCount(Integer.parseInt(sb.toString()));
        }
    }

    @Override
    public void characters(char[] c, int start, int length) throws SAXException {
        if (sb != null) {
            sb.append(c, start, length);
        }
    }

    public Diagrams getDiagrams() {
        return diagrams;
    }
    
}
