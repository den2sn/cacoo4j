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

import cacoo4j.License;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author den2sn
 */
public class LicenseHandler extends AbstructHandler {
    private License license;
    private StringBuilder sb;

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException {
        if("license".equals(qName)) {
            license = new License();
        } else if("plan".equals(qName) ||
                  "remainingSheets".equals(qName) ||
                  "remainingSharedFolders".equals(qName) ||
                  "maxNumberOfSharersPerDiagram".equals(qName) ||
                  "maxNumberOfSharersPerSharedFolder".equals(qName) ||
                  "canCreateSheet".equals(qName) ||
                  "canCreateSharedFolder".equals(qName) ||
                  "canExportVectorFormat".equals(qName)) {
            sb = new StringBuilder();
        }
    }

    @Override
    public void endElement(String namespaceURI,String localName,String qName) throws SAXException {
        if("plan".equals(qName)) {
            license.setPlan(sb.toString());
            sb = null;
        } else if("remainingSheets".equals(qName)) {
            license.setRemainingSheets(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("remainingSharedFolders".equals(qName)) {
            license.setRemainingSharedFolders(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("maxNumberOfSharersPerDiagram".equals(qName)) {
            license.setMaxNumberOfSharersPerDiagram(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("maxNumberOfSharersPerSharedFolder".equals(qName)) {
            license.setMaxNumberOfSharersPerSharedFolder(Integer.parseInt(sb.toString()));
            sb = null;
        } else if("canCreateSheet".equals(qName)) {
            license.setCanCreateSheet(Boolean.parseBoolean(sb.toString()));
            sb = null;
        } else if("canCreateSharedFolder".equals(qName)) {
            license.setCanCreateSharedFolder(Boolean.parseBoolean(sb.toString()));
            sb = null;
        } else if("canExportVectorFormat".equals(qName)) {
            license.setCanExportVectorFormat(Boolean.parseBoolean(sb.toString()));
            sb = null;
        }
    }

    @Override
    public void characters(char[] c, int start, int length) throws SAXException {
        if (sb != null) {
            sb.append(c, start, length);
        }
    }

    public License getLicense() {
        return license;
    }
    
}
