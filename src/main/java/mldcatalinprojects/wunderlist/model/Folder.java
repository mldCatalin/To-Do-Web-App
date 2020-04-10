package mldcatalinprojects.wunderlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity(name = "folder")
@Table(name = "folder")
public class Folder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folderId")
    private Integer id;
    
    @Column
    private String name;
    
    @OneToMany(
            mappedBy = "folder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ListInFolder> toDoLists = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User owner;
    
    @Column(name = "folderOrder", nullable = false)
    private Integer order;
    
    public void addToDoList(ToDoList toDoList){
        ListInFolder listInFolder = new ListInFolder(this, toDoList);
        toDoLists.add(listInFolder);
    }
    
    public void removeToDoLIst(ToDoList toDoList){
        for(Iterator<ListInFolder> iterator = toDoLists.iterator(); iterator.hasNext();){
            ListInFolder listInFolder = iterator.next();
            
            if(listInFolder.getFolder().equals(this) && listInFolder.getToDoList().equals(toDoList)){
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
}
