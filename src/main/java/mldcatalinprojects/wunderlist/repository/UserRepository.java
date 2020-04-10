package mldcatalinprojects.wunderlist.repository;

import mldcatalinprojects.wunderlist.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;


public interface UserRepository extends CrudRepository<User, Integer> {
    
    
    User findUserByEmail(@RequestParam String email);
    
    User findUserById(Integer userId);
}