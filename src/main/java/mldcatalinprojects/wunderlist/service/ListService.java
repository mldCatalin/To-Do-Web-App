package mldcatalinprojects.wunderlist.service;

import mldcatalinprojects.wunderlist.exception.InputValidationException;
import mldcatalinprojects.wunderlist.exception.ResourceExistsException;
import mldcatalinprojects.wunderlist.model.ToDoList;
import mldcatalinprojects.wunderlist.model.ListDTO;
import mldcatalinprojects.wunderlist.model.User;
import mldcatalinprojects.wunderlist.repository.ListRepository;
import mldcatalinprojects.wunderlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class ListService {
    
    private ListRepository listRepository;
    private UserRepository userRepository;
    
    public ListService(@Autowired ListRepository listRepository, UserRepository userRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
    }
    
    public ToDoList addList(ListDTO newList) {
        if(isEmpty(newList.getName()) || isEmpty(newList.getUserId())){
            throw new InputValidationException("Missing toDoList name and/or user ID");
        }
    
        User owner = userRepository.findUserById(newList.getUserId());
        ToDoList existingToDoList = listRepository.findListByNameAndUserId(newList.getName(), owner.getId());
        if(existingToDoList != null){
            throw new ResourceExistsException("A toDoList with this name already exists");
        }
    
        ToDoList toDoList = new ToDoList();
        toDoList.setName(newList.getName());
        toDoList.setCreatedByUser(owner);
        
       
        return listRepository.save(toDoList);
    }
}
