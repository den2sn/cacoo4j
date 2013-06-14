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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author den2sn
 */
public class CommentHandler extends AbstructHandler {
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
        if ("comment".equals(qName)) {
            comment = new Comment();
        } else if("user".equals(qName)) {
            user = new CacooUser();
        } else if("name".equals(qName) ||
                  "nickname".equals(qName) ||
                  "type".equals(qName) ||
                  "imageUrl".equals(qName) ||
                  "created".equals(qName) ||
                  "updated".equals(qName) ||
                  "content".equals(qName)) {
            sb = new StringBuilder();
        }
    }

    @Override
    public void endElement(String namespaceURI,String localName,String qName) throws SAXException {
        if("user".equals(qName)) {
            comment.setUser(user);
            user = null;
        } else if("imageUrl".equals(qName)) {
            user.setImageUrl(sb.toString());
            sb = null;
        } else if("type".equals(qName)) {
            user.setType(sb.toString());
            sb = null;
        } else if("name".equals(qName)) {
            user.setName(sb.toString());
            sb = null;
        } else if("nickname".equals(qName)) {
            user.setNickname(sb.toString());
            sb = null;
        } else if("created".equals(qName)) {
            try {
                comment.setCreated(format.parse(sb.toString()));
            } catch(Exception e) {
            }
            sb = null;
        } else if("updated".equals(qName)) {
            try {
                comment.setUpdated(format.parse(sb.toString()));
            } catch(Exception e) {
            }
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

    public Comment getComment() {
        return comment;
    }
    
}
