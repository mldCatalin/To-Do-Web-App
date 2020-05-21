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
    
    @Column(name = "to_do_list_id")
    private Integer toDoListId;
    
    @Column(name = "folder_id")
    private Integer folderId;
    
    public ListInFolderId(Integer toDoListId, Integer folderId) {
        this.toDoListId = toDoListId;
        this.folderId = folderId;
    }
    
    //hibernate needs a default constructor to work
    public ListInFolderId() {
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass())
            return false;
        
        ListInFolderId that = (ListInFolderId) o;
        return Objects.equals(toDoListId, that.toDoListId) &&
                Objects.equals(folderId, that.folderId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(toDoListId, folderId);
    }
    
    public Integer getToDoListId() {
        return toDoListId;
    }
    
    public void setToDoListId(Integer toDoListId) {
        this.toDoListId = toDoListId;
    }
    
    public Integer getFolderId() {
        return folderId;
    }
    
    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }
}
