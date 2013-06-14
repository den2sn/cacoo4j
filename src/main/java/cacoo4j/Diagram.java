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
import java.util.Date;
import java.util.List;

/**
 *
 * @author den2sn
 */
public class Diagram implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SECURITY_PRIVATE = "private";
    public static final String SECURITY_URL = "url";
    public static final String SECURITY_PUBLIC = "public";
    public static final String TYPE_NORMAL = "normal";
    public static final String TYPE_STENCIL = "stencil";
    public static final String TYPE_TEMPLATE = "template";

    private String url;
    private String imageUrl;
    private String imageUrlForApi;
    private String diagramId;
    private String title;
    private String description;
    private String security;
    private String type;
    private CacooUser owner;
    private boolean editing;
    private boolean own;
    private boolean shared;
    private int folderId;
    private String folderName;
    private int sheetCount;
    private Date created;
    private Date updated;
    private List<Sheet> sheets;
    private List<Comment> comments;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlForApi() {
        return imageUrlForApi;
    }

    public void setImageUrlForApi(String imageUrlForApi) {
        this.imageUrlForApi = imageUrlForApi;
    }

    public String getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(String diagramId) {
        this.diagramId = diagramId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CacooUser getOwner() {
        return owner;
    }

    public void setOwner(CacooUser owner) {
        this.owner = owner;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getSheetCount() {
        return sheetCount;
    }

    public void setSheetCount(int sheetCount) {
        this.sheetCount = sheetCount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
