package mldcatalinprojects.wunderlist.repository;


import mldcatalinprojects.wunderlist.model.ToDoList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ListRepository extends CrudRepository<ToDoList, Integer> {
    
    ToDoList findListByName(String name);
    
    @Query(value = "SELECT * FROM to_do_list as list WHERE list.name = ?1 AND list.created_by_user_id = ?2", nativeQuery = true)
    ToDoList findListByNameAndUserId(@RequestParam String name, @RequestParam Integer userId);
    
}
