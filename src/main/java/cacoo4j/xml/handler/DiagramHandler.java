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
import cacoo4j.Comment;
import cacoo4j.Diagram;
import cacoo4j.Sheet;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author den2sn
 */
public class DiagramHandler extends AbstructHandler {
    private Diagram diagram;
    private CacooUser owner;
    private List<Sheet> sheets;
    private Sheet sheet;
    private List<Comment> comments;
    private Comment comment;
    private CacooUser user;
    private StringBuilder sb;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {
        if("diagram".equals(qName)) {
            diagram = new Diagram();
        } else if("owner".equals(qName)) {
            owner = new CacooUser();
        } else if("sheets".equals(qName)) {
            sheets = new ArrayList<Sheet>();
        } else if("sheet".equals(qName)) {
            sheet = new Sheet();
        } else if("comments".equals(qName)) {
            comments = new ArrayList<Comment>();
        } else if("comment".equals(qName)) {
            comment = new Comment();
        } else if("user".equals(qName)) {
            user = new CacooUser();
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
                  "uid".equals(qName) ||
                  "width".equals(qName) ||
                  "height".equals(qName) ||
                  "content".equals(qName)) {
            sb = new StringBuilder();
        }
    }

    @Override
    public void endElement(String namespaceURI,String localName,String qName) throws SAXException {
        if("sheets".equals(qName)) {
            diagram.setSheets(sheets);
            sheets = null;
        } else if("sheet".equals(qName)) {
            sheets.add(sheet);
            sheet = null;
        } else if("comments".equals(qName)) {
            diagram.setComments(comments);
            comments = null;
        } else if("comment".equals(qName)) {
            comments.add(comment);
            comment = null;
        } else if("user".equals(qName)) {
            comment.setUser(user);
            user = null;
        } else if("owner".equals(qName)) {
            diagram.setOwner(owner);
            owner = null;
        } else if("url".equals(qName)) {
            if (sheet != null) {
                sheet.setUrl(sb.toString());
            } else {
                diagram.setUrl(sb.toString());
            }
            sb = null;
        } else if("imageUrl".equals(qName)) {
            if (user != null) {
                user.setImageUrl(sb.toString());
            } else if (sheet != null) {
                sheet.setImageUrl(sb.toString());
            } else if (owner != null) {
                owner.setImageUrl(sb.toString());
            } else {
                diagram.setImageUrl(sb.toString());
            }
            sb = null;
        } else if("imageUrlForApi".equals(qName)) {
            if (sheet != null) {
                sheet.setImageUrlForApi(sb.toString());
            } else {
                diagram.setImageUrlForApi(sb.toString());
            }
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
            if (user != null) {
                user.setType(sb.toString());
            } else if (owner != null) {
                owner.setType(sb.toString());
            } else {
                diagram.setType(sb.toString());
            }
            sb = null;
        } else if("name".equals(qName)) {
            if (user != null) {
                user.setName(sb.toString());
            } else if (sheet != null) {
                sheet.setName(sb.toString());
            } else if (owner != null) {
                owner.setName(sb.toString());
            }
            sb = null;
        } else if("nickname".equals(qName)) {
            if (user != null) {
                user.setNickname(sb.toString());
            } else if (owner != null) {
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
                if (comment != null) {
                    comment.setCreated(format.parse(sb.toString()));
                } else {
                    diagram.setCreated(format.parse(sb.toString()));
                }
            } catch(Exception e) {
            }
            sb = null;
        } else if("updated".equals(qName)) {
            try {
                if (comment != null) {
                    comment.setUpdated(format.parse(sb.toString()));
                } else {
                    diagram.setUpdated(format.parse(sb.toString()));
                }
            } catch(Exception e) {
            }
            sb = null;
        } else if("uid".equals(qName)) {
            sheet.setUid(sb.toString());
            sb = null;
        } else if("width".equals(qName)) {
            sheet.setWidth(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("height".equals(qName)) {
            sheet.setHeight(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("content".equals(qName)) {
            comment.setContent(sb.toString());
            sb = null;
        }
    }

    @Override
    public void characters(char[] c, int start, int length) throws SAXException {
        if (sb != null) {
            sb.append(c, start, length);
        }
    }

    public Diagram getDiagram() {
        return diagram;
    }
    
}
