package mldcatalinprojects.wunderlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity(name = "Folder")
@Table(name = "folder")
public class Folder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @OneToMany(
            mappedBy = "folder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Fetch(FetchMode.JOIN)
    private List<ListInFolder> toDoLists = new ArrayList<>();
    
    /**
     * JoinColumn references this annotated member
     * to its corresponding column in the parent table
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User owner;
    
    @Column(name = "folder_order", nullable = false)
    private Integer order;
    
    private Folder() {
    }
    
    public Folder(String name, User owner, Integer order) {
        this.name = name;
        this.owner = owner;
        this.order = order;
    }
    
    public void addToDoList(ToDoList toDoList) {
        ListInFolder listInFolder = new ListInFolder(this, toDoList, toDoLists.size() + 1, false);
        toDoLists.add(listInFolder);
    }
    
    public void removeToDoLIst(ToDoList toDoList) {
        for (Iterator<ListInFolder> iterator = toDoLists.iterator(); iterator.hasNext(); ) {
            ListInFolder listInFolder = iterator.next();
            
            if (listInFolder.getFolder().equals(this) && listInFolder.getToDoList().equals(toDoList)) {
                iterator.remove();
                listInFolder.setFolder(null);
                listInFolder.setToDoList(null);
            }
        }
    }
    
    public Integer getId() {
        return id;
    }
    
    public List<ListInFolder> getToDoLists() {
        return toDoLists;
    }
    
    public User getOwner() {
        return owner;
    }
    
    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getOrder() {
        return order;
    }
    
    public void setOrder(Integer order) {
        this.order = order;
    }
    
    public List<Integer> getToListIds() {
        List<Integer> ids = new ArrayList<>();
        for (ListInFolder listinFolder :
                getToDoLists()) {
            ids.add(listinFolder.getToDoList().getId());
        }
    return ids;
    }
    
    //ToDo: Some exceptions here maybe.. NullPointer Alert!
    public ToDoList getToDoListById(Integer existingId) {
        for (ListInFolder listInFolder : getToDoLists()) {
            if(listInFolder.getToDoList().getId() == existingId)
                return listInFolder.getToDoList();
        }
        return null;
    }
}
