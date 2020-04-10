package mldcatalinprojects.wunderlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name="folder")
public class Folder {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folderId")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User owner;
    
    @Column
    private String name;
    
    @Column
    private Integer order;
    
    public Folder() {
    }
    
    public Integer getId() {
        return id;
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
