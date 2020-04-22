package mldcatalinprojects.wunderlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ToDoList")
@Table(name = "to_do_list")
public class ToDoList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    
    //@NaturalId
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * JoinColumn references this annotated member
     * to its corresponding column in the parent table
     */
    @ManyToOne
    @JoinColumn(name = "created_by_user_id", nullable = false)
    @JsonIgnore
    private User createdByUser;
    
    
    ToDoList() {
    }
    
    public ToDoList(String name, User createdByUser) {
        this.name = name;
        this.createdByUser = createdByUser;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        
        if (o == null || getClass() != o.getClass())
            return false;
        
        ToDoList that = (ToDoList) o;
        return Objects.equals(this.id, that.getId());
    }
    
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
