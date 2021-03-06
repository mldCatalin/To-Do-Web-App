package mldcatalinprojects.wunderlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class ToDoListDTO {
    
    private Integer id;
    
    private String name;
    
    @JsonProperty(access = READ_ONLY)
    private Integer userId;
    
    @JsonProperty(access = READ_ONLY)
    private Integer order;
    
    public ToDoListDTO() {
    }
    
    public ToDoListDTO(Integer id, String name, Integer userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getOrder() {
        return order;
    }
    
    public void setOrder(Integer order) {
        this.order = order;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
