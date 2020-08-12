package mldcatalinprojects.wunderlist.service;

import mldcatalinprojects.wunderlist.exception.InputValidationException;
import mldcatalinprojects.wunderlist.exception.ResourceExistsException;
import mldcatalinprojects.wunderlist.model.*;
import mldcatalinprojects.wunderlist.repository.FolderRepository;
import mldcatalinprojects.wunderlist.repository.ListRepository;
import mldcatalinprojects.wunderlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class ListService {
    
    public static final String HIDDEN = "";
    
    private ListRepository listRepository;
    private UserRepository userRepository;
    private FolderRepository folderRepository;
    private FolderService folderService;
    
    public ListService(@Autowired ListRepository listRepository,
                       @Autowired UserRepository userRepository,
                       @Autowired FolderRepository folderRepository,
                       @Autowired FolderService folderService) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
        this.folderRepository = folderRepository;
        this.folderService = folderService;
    }
    
    public ToDoListDTO addNewList(ToDoListDTO newToDoList) {
        if (isEmpty(newToDoList.getName()) || isEmpty(newToDoList.getUserId())) {
            throw new InputValidationException("Missing toDoList name and/or user ID");
        }
    
        User owner = userRepository.findUserById(newToDoList.getUserId());
        ToDoList toDoList = ensureToDoList(newToDoList, owner);
    
        Folder envelope = new Folder(HIDDEN, toDoList.getCreatedByUser(), getFolderOrder(owner));
        envelope.addToDoList(toDoList);
        folderRepository.save(envelope);
        
        return convertListToDTO(toDoList);
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
    
    //TODO: work should be delegated to folderService
    public FolderDTO moveToDoList(Integer id, Integer newFolderId) {
        ToDoList toDoList = listRepository.getToDoListById(id);
        Folder currentToDoListFolder = folderRepository.getToDoListContainingFolder(id);
        Folder newToDoListFolder = folderRepository.getFolderById(newFolderId);
        
        newToDoListFolder.addToDoList(toDoList);
        currentToDoListFolder.removeToDoLIst(toDoList);
        
        folderRepository.save(currentToDoListFolder);
        folderRepository.save(newToDoListFolder);
        
//        if(folderIsEmpty(currentToDoListFolder.getId())){
//            folderRepository.deleteById(currentToDoListFolder.getId());
//        }
//        else{
//            folderRepository.save(currentToDoListFolder);
//        }
//        folderRepository.save(newToDoListFolder);
        
        return folderService.convertToDTO(newToDoListFolder);
    }
    
    private boolean folderIsEmpty(Integer folderId) {
        return folderRepository.getFolderById(folderId).getToDoLists().size() == 0;
    }
    
    private ToDoListDTO convertListToDTO(ToDoList toDoList) {
        User toDoListOwner = toDoList.getCreatedByUser();
        return new ToDoListDTO(toDoList.getId(), toDoList.getName(), toDoListOwner.getId(), getToDoListOrder(toDoList));
    }
    
    private Integer getToDoListOrder(ToDoList toDoList) {
        Integer order = null;
        Folder containingFolder = folderRepository.getToDoListContainingFolder(toDoList.getId());
        List<ListInFolder> folders = toDoList.getFolders();
        for (ListInFolder listInFolder : folders) {
            if (listInFolder.getFolder().getId().equals(containingFolder.getId())){
                order = listInFolder.getListOrder();
            }
        }
        return order;
    }
    
    public void deleteToDoList(Integer toDoListId) {
        ToDoList listToDelete = listRepository.getToDoListById(toDoListId);
        listRepository.delete(listToDelete);
    }
}
