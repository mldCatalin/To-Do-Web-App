package mldcatalinprojects.wunderlist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_WRITE;

public class ToDoListDTO {
    
    @JsonProperty(access = READ_ONLY)
    private Integer id;
    
    @JsonProperty(access = READ_WRITE)
    private String name;
    
    @JsonProperty(access = READ_WRITE)
    private Integer userId;
    
    @JsonProperty(access = READ_ONLY)
    private Integer order;
    
    public ToDoListDTO() {
    }
    
    public ToDoListDTO(Integer id, String name, Integer userId, Integer order) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.order = order;
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
