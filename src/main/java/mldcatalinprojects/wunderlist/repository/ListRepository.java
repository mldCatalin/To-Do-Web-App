package mldcatalinprojects.wunderlist.repository;


import mldcatalinprojects.wunderlist.model.ToDoList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListRepository extends CrudRepository<ToDoList, Integer> {
    
    ToDoList findListByName(String name);
    
    List<ToDoList> findListByCreatedByUserId(Integer createdByUserId);
    
}
