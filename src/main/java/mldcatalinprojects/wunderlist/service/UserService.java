package mldcatalinprojects.wunderlist.service;

import mldcatalinprojects.wunderlist.exception.InputValidationException;
import mldcatalinprojects.wunderlist.exception.ResourceExistsException;
import mldcatalinprojects.wunderlist.exception.ResourceNotFoundException;
import mldcatalinprojects.wunderlist.model.User;
import mldcatalinprojects.wunderlist.model.UserDTO;
import mldcatalinprojects.wunderlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User addUser(UserDTO newUser) {
        
        if (isEmpty(newUser.getName()) || isEmpty(newUser.getEmail())) {
            throw new InputValidationException("Missing name and/or email");
        }
        
        User userByEmail = userRepository.findUserByEmail(newUser.getEmail());
        if (userByEmail != null) {
            throw new ResourceExistsException("This email is already registered");
        }
        
        User user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        userRepository.save(user);
        return user;
    }
    
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
    
    public Integer getUserByEmail(String email) {
        User existingUser = userRepository.findUserByEmail(email);
        if (existingUser == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return existingUser.getId();
    }
}
