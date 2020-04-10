package mldcatalinprojects.wunderlist.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "listinfolder")
@Table(name = "listinfolder")
public class ListInFolder {

    @Id
    @Embedded
    private ListInFolderId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("listId")
    private ToDoList toDoList;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("folderId")
    private Folder folder;
    
    @Column(name = "listOrder", nullable = false)
    private Integer order;
    
    @Column(name = "dnd")
    private boolean doNotDisturb;
    
    private ListInFolder(){}
    
    public ListInFolder(Folder folder, ToDoList toDoList) {
        this.toDoList = toDoList;
        this.folder = folder;
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
    
    public Integer getOrder() {
        return order;
    }
    
    public void setOrder(Integer order) {
        this.order = order;
    }
    
    public boolean isDoNotDisturb() {
        return doNotDisturb;
    }
    
    public void setDoNotDisturb(boolean doNotDisturb) {
        this.doNotDisturb = doNotDisturb;
    }
}
