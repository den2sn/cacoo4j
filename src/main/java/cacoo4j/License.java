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

/**
 *
 * @author den2sn
 */
public class License implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String plan;
    private int remainingSheets;
    private int remainingSharedFolders;
    private int maxNumberOfSharersPerDiagram;
    private int maxNumberOfSharersPerSharedFolder;
    private boolean canCreateSheet;
    private boolean canCreateSharedFolder;
    private boolean canExportVectorFormat;

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getRemainingSheets() {
        return remainingSheets;
    }

    public void setRemainingSheets(int remainingSheets) {
        this.remainingSheets = remainingSheets;
    }

    public int getRemainingSharedFolders() {
        return remainingSharedFolders;
    }

    public void setRemainingSharedFolders(int remainingSharedFolders) {
        this.remainingSharedFolders = remainingSharedFolders;
    }

    public int getMaxNumberOfSharersPerDiagram() {
        return maxNumberOfSharersPerDiagram;
    }

    public void setMaxNumberOfSharersPerDiagram(int maxNumberOfSharersPerDiagram) {
        this.maxNumberOfSharersPerDiagram = maxNumberOfSharersPerDiagram;
    }

    public int getMaxNumberOfSharersPerSharedFolder() {
        return maxNumberOfSharersPerSharedFolder;
    }

    public void setMaxNumberOfSharersPerSharedFolder(int maxNumberOfSharersPerSharedFolder) {
        this.maxNumberOfSharersPerSharedFolder = maxNumberOfSharersPerSharedFolder;
    }

    public boolean isCanCreateSheet() {
        return canCreateSheet;
    }

    public void setCanCreateSheet(boolean canCreateSheet) {
        this.canCreateSheet = canCreateSheet;
    }

    public boolean isCanCreateSharedFolder() {
        return canCreateSharedFolder;
    }

    public void setCanCreateSharedFolder(boolean canCreateSharedFolder) {
        this.canCreateSharedFolder = canCreateSharedFolder;
    }

    public boolean isCanExportVectorFormat() {
        return canExportVectorFormat;
    }

    public void setCanExportVectorFormat(boolean canExportVectorFormat) {
        this.canExportVectorFormat = canExportVectorFormat;
    }
    
    
}
