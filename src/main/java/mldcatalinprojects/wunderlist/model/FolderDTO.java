package mldcatalinprojects.wunderlist.model;

import java.util.ArrayList;
import java.util.List;

public class FolderDTO {
    
    private Integer id;
    
    private String name;
    
    private Integer order;
    
    private List<ToDoListDTO> toDoLists = new ArrayList<>();
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<ToDoListDTO> getToDoLists() {
        return toDoLists;
    }
    
    public void setToDoLists(List<ToDoListDTO> toDoLists) {
        this.toDoLists = toDoLists;
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
