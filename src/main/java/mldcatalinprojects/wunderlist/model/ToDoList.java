package mldcatalinprojects.wunderlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "list")
@Table(name = "list")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ToDoList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listId")
    private Integer id;
    
    
    @NaturalId
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * JoinColumn references this annotated member
     * to its corresponding column in the parent table
     */
    @ManyToOne
    @JoinColumn(name = "createdByUserId", nullable = false)
    @JsonIgnore
    private User createdByUser;
    
    private ToDoList(){}
    
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
