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

import cacoo4j.Folder;
import cacoo4j.Folders;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author den2sn
 */
public class FoldersHandler extends AbstructHandler {
    private Folders folders;
    private List<Folder> result;
    private Folder folder;
    private StringBuilder sb;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {
        if("folders".equals(qName)) {
            folders = new Folders();
        } else if("result".equals(qName)) {
            result = new ArrayList<Folder>();
        } else if("folder".equals(qName)) {
            folder = new Folder();
        } else if("folderId".equals(qName) ||
                  "folderName".equals(qName) ||
                  "type".equals(qName) ||
                  "created".equals(qName) ||
                  "updated".equals(qName)) {
            sb = new StringBuilder();
        }
    }

    @Override
    public void endElement(String namespaceURI,String localName,String qName) throws SAXException {
        if("result".equals(qName)) {
            folders.setResult(result);
            result = null;
        } else if("folder".equals(qName)) {
            result.add(folder);
            folder = null;
        } else if("folderId".equals(qName)) {
            folder.setFolderId(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("folderName".equals(qName)) {
            folder.setFolderName(sb.toString());
            sb = null;
        } else if("type".equals(qName)) {
            folder.setType(sb.toString());
            sb = null;
        } else if("created".equals(qName)) {
            try {
                folder.setCreated(format.parse(sb.toString()));
            } catch(Exception e) {
            }
            sb = null;
        } else if("updated".equals(qName)) {
            try {
                folder.setUpdated(format.parse(sb.toString()));
            } catch(Exception e) {
            }
            sb = null;
        }
    }

    @Override
    public void characters(char[] c, int start, int length) throws SAXException {
        if (sb != null) {
            sb.append(c, start, length);
        }
    }

    public Folders getFolders() {
        return folders;
    }
    
}
