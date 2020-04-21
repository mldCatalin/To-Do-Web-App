package mldcatalinprojects.wunderlist.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ListInFolder")
@Table(name = "list_in_folder")
public class ListInFolder {
    
    @EmbeddedId
    private ListInFolderId id;
    
    /**
     * MapsId designates a ManyToOne relationship attribute that provides the
     * mapping for an EmbeddedId primary key
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("toDoListId")
    private ToDoList toDoList;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("folderId")//literally tells H where to copy the folder's Id
    // the value of the id in folder is copied onto the "folderId" member in the embedded id
    private Folder folder;
    
    @Column(name = "list_order", nullable = false)
    private Integer listOrder;
    
    @Column(name = "dnd")
    private boolean doNotDisturb;
    
    private ListInFolder() {
    }
    
    public ListInFolder(Folder folder, ToDoList toDoList, Integer listOrder, boolean doNotDisturb) {
        this.toDoList = toDoList;
        this.folder = folder;
        this.listOrder = listOrder;
        this.doNotDisturb = doNotDisturb;
        this.id = new ListInFolderId(toDoList.getId(), folder.getId());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass())
            return false;
        
        ListInFolder that = (ListInFolder) o;
        return Objects.equals(toDoList, that.toDoList) &&
                Objects.equals(folder, that.folder);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(toDoList, folder);
    }
    
    public ListInFolderId getId() {
        return id;
    }
    
    public void setId(ListInFolderId id) {
        this.id = id;
    }
    
    public ToDoList getToDoList() {
        return toDoList;
    }
    
    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }
    
    public Folder getFolder() {
        return folder;
    }
    
    public void setFolder(Folder folder) {
        this.folder = folder;
    }
    
    public Integer getListOrder() {
        return listOrder;
    }
    
    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }
    
    public boolean isDoNotDisturb() {
        return doNotDisturb;
    }
    
    public void setDoNotDisturb(boolean doNotDisturb) {
        this.doNotDisturb = doNotDisturb;
    }
}
