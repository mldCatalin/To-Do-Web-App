package mldcatalinprojects.wunderlist.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity(name="list")
public class ToDoList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="listId")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User createdByUser;
    
    @Column
    private String name;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public User getCreatedByUser() {
        return createdByUser;
    }
    
    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
