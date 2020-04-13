package mldcatalinprojects.wunderlist.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * This is the Composite Primary Key for the ListInFolder entity
 */
@Embeddable
public class ListInFolderId implements Serializable {
    
    @Column(name = "list_id")
    private Integer listId;
    
    @Column(name = "folder_id")
    private Integer folderId;
    
    public ListInFolderId(Integer listId, Integer folderId) {
        this.listId = listId;
        this.folderId = folderId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass())
            return false;
        
        ListInFolderId that = (ListInFolderId) o;
        return Objects.equals(listId, that.listId) &&
                Objects.equals(folderId, that.folderId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(listId, folderId);
    }
    
    public Integer getListId() {
        return listId;
    }
    
    public void setListId(Integer listId) {
        this.listId = listId;
    }
    
    public Integer getFolderId() {
        return folderId;
    }
    
    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }
}
