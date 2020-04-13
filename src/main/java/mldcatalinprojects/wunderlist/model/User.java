package mldcatalinprojects.wunderlist.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "name")
    private String name;
    
    /**
     * mappedBy references the annotated variable to its corresponding member
     * inside the child entity (name matches member name not column)
     */
    @OneToMany(mappedBy = "owner")
    private List<Folder> folders;
    
    /**
     * mappedBy references the annotated variable to its corresponding member
     * inside the child entity (name matches member name not column)
     */
    @OneToMany(mappedBy = "createdByUser")
    private List<ToDoList> toDoLists;
    
    public List<ToDoList> getToDoLists() {
        return toDoLists;
    }
    
    public void setToDoLists(List<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }
    
    public List<Folder> getFolders() {
        return folders;
    }
    
    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
