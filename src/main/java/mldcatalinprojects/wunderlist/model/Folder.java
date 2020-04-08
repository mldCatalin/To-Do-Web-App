package mldcatalinprojects.wunderlist.model;

import javax.persistence.*;

@Entity(name="folder")
public class Folder {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folderId")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    
    @Column
    private String name;
    
    @Column
    private Integer order;
    
    public Folder() {
    }
    
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
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
