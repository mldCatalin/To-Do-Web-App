package mldcatalinprojects.wunderlist.model;

public class ListDTO {
    
    private String name;
    
    private Integer userId;
    
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
