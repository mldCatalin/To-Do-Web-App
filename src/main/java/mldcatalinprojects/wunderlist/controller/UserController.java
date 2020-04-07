package mldcatalinprojects.wunderlist.controller;

import mldcatalinprojects.wunderlist.service.UserService;
import mldcatalinprojects.wunderlist.model.UserDTO;
import mldcatalinprojects.wunderlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /user (after Application path)
public class UserController {
    
    private UserService userService;
    
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping (consumes = "application/json")// Map ONLY POST Requests
    public @ResponseBody User addNewUser (@RequestBody UserDTO newUser
            , HttpServletResponse response) throws Exception {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        
        return userService.addUser(newUser);
        
        
        
        
        
        

//        try {
//            user
//            return "Saved User with name: " + user.getName() + " and Email: " + user.getEmail();
//        } catch (Exception e) {
//            response.sendError(422, "Missing 'name' and/or 'email' entity from request");
//            return "Request Fail";
//        }
    }
    
//    @GetMapping
//    public @ResponseBody Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//
//    }
}
