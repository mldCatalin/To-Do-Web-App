package mldcatalinprojects.wunderlist.service;

import mldcatalinprojects.wunderlist.exception.InputValidationException;
import mldcatalinprojects.wunderlist.exception.ResourceExistsException;
import mldcatalinprojects.wunderlist.model.Folder;
import mldcatalinprojects.wunderlist.model.ToDoListDTO;
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
    
    public ListService(@Autowired ListRepository listRepository, @Autowired UserRepository userRepository, @Autowired FolderRepository folderRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
        this.folderRepository = folderRepository;
    }
    
    public ToDoList addNewList(ToDoListDTO newToDoList) {
        if (isEmpty(newToDoList.getName()) || isEmpty(newToDoList.getUserId())) {
            throw new InputValidationException("Missing toDoList name and/or user ID");
        }
    
        User owner = userRepository.findUserById(newToDoList.getUserId());
        ToDoList toDoList = ensureToDoList(newToDoList, owner);
    
        Folder envelope = new Folder(HIDDEN, toDoList.getCreatedByUser(), getFolderOrder(owner));
        envelope.addToDoList(toDoList);
        folderRepository.save(envelope);
        
        return toDoList;
    }
    
    private ToDoList ensureToDoList(ToDoListDTO newToDoList, User owner){
        ToDoList existingToDoList = listRepository.findListByNameAndUserId(newToDoList.getName(), owner.getId());
        if (existingToDoList != null) {
            throw new ResourceExistsException("A toDoList with this name already exists");
        }
        return new ToDoList(newToDoList.getName(), owner);
    }
    
    private int getFolderOrder(User user) {
        return folderRepository.countFolderByUserId(user.getId()) + 1;
    }
}
