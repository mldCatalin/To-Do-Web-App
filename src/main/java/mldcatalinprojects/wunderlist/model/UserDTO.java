package mldcatalinprojects.wunderlist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * @author Catalin Moldovan
 */
public class UserDTO {
    
    @JsonProperty(access = READ_ONLY)
    private Integer id;
    
    private String name;
    private String email;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    
}
