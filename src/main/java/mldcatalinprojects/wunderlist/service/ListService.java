package mldcatalinprojects.wunderlist.service;

import mldcatalinprojects.wunderlist.exception.InputValidationException;
import mldcatalinprojects.wunderlist.exception.ResourceExistsException;
import mldcatalinprojects.wunderlist.model.Folder;
import mldcatalinprojects.wunderlist.model.ListDTO;
import mldcatalinprojects.wunderlist.model.ToDoList;
import mldcatalinprojects.wunderlist.model.User;
import mldcatalinprojects.wunderlist.repository.FolderRepository;
import mldcatalinprojects.wunderlist.repository.ListRepository;
import mldcatalinprojects.wunderlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class ListService {
    
    public static final String HIDDEN = "";
    
    private ListRepository listRepository;
    private UserRepository userRepository;
    private FolderRepository folderRepository;
    
    public ListService(@Autowired ListRepository listRepository, UserRepository userRepository, FolderRepository folderRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
        this.folderRepository = folderRepository;
    }
    
    public ToDoList addList(ListDTO newList) {
        if (isEmpty(newList.getName()) || isEmpty(newList.getUserId())) {
            throw new InputValidationException("Missing toDoList name and/or user ID");
        }
        
        User owner = userRepository.findUserById(newList.getUserId());
        ToDoList existingToDoList = listRepository.findListByNameAndUserId(newList.getName(), owner.getId());
        if (existingToDoList != null) {
            throw new ResourceExistsException("A toDoList with this name already exists");
        }
        
        ToDoList toDoList = new ToDoList();
        toDoList.setName(newList.getName());
        toDoList.setCreatedByUser(owner);
        
        //TODO: constructor with the mandatory fields
        //TODO: check other entities for non-nullable cols
        Folder envelope = new Folder();
        envelope.setName(HIDDEN);
        envelope.setOwner(owner);
        envelope.addToDoList(toDoList);
        envelope.setOrder(1);
        Folder savedEnvelope = folderRepository.save(envelope);
    
    
        return toDoList;
    }
}
