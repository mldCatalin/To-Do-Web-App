package mldcatalinprojects.wunderlist.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mldcatalinprojects.wunderlist.model.User;
import org.springframework.web.bind.annotation.RequestParam;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    
    User findUserByEmail(@RequestParam String email);
    
    User findUserById(Integer userId);
}