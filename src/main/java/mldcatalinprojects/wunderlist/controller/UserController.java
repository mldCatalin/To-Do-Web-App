package mldcatalinprojects.wunderlist.controller;

import mldcatalinprojects.wunderlist.model.EmailDTO;
import mldcatalinprojects.wunderlist.model.User;
import mldcatalinprojects.wunderlist.model.UserDTO;
import mldcatalinprojects.wunderlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    
    private UserService userService;
    
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping(consumes = "application/json")
    public @ResponseBody
    User addUser(@RequestBody UserDTO newUser
            , HttpServletResponse response) throws Exception {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        
        return userService.addUser(newUser);
    }
    
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getUsers() {
        return userService.getUsers();
    }
    
    @GetMapping(consumes = "application/json")
    public @ResponseBody
    User getUser(@RequestBody EmailDTO email) {
        return userService.getUserByEmail(email);
    }
}
