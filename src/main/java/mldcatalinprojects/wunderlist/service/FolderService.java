package mldcatalinprojects.wunderlist.service;

import mldcatalinprojects.wunderlist.model.*;
import mldcatalinprojects.wunderlist.repository.FolderRepository;
import mldcatalinprojects.wunderlist.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FolderService {
    
    private FolderRepository folderRepository;
    private ListRepository listRepository;
    
    public FolderService(@Autowired FolderRepository folderRepository, @Autowired ListRepository listRepository) {
        this.folderRepository = folderRepository;
        this.listRepository = listRepository;
    }
    
    //ToDo: Method not final. needs more testing and development before implementation
    public FolderDTO patchFolder(Integer id, UpdatedIds updatedIds) {
        Folder folderToBePatched = folderRepository.findFolderById(id);
        if (folderToBePatched.getToDoLists().size() > updatedIds.getToDoListIds().size()) {
            return cleanFolder(folderToBePatched, updatedIds);
        } else {
            return enrichFolder(folderToBePatched, updatedIds);
        }
    }
    
    //ToDo: Method not final. needs more testing and development before implementation
    //It deletes list from db when removing from folder
    private FolderDTO cleanFolder(Folder folderToBePatched, UpdatedIds updatedListIds) {
        List<Integer> currentToDoListIds = folderToBePatched.getToListIds();
        
        for (Integer existingId : currentToDoListIds) {
            if (!updatedListIds.getToDoListIds().contains(existingId)) {
                ToDoList listToRemove = folderToBePatched.getToDoListById(existingId);
                folderToBePatched.removeToDoLIst(listToRemove);
            }
        }
        folderRepository.save(folderToBePatched);
        return convertToDTO(folderToBePatched);
    }
    
    //ToDo: Method not final. needs more testing and development before implementation
    //It duplicates the list for current user, when adding it to the folder.
    //one in the original place, a copy added in the folder
    private FolderDTO enrichFolder(Folder folderToBePatched, UpdatedIds updatedListIds) {
        List<Integer> currentToDoListIds = folderToBePatched.getToListIds();
        
        for (Integer updatedListId : updatedListIds.getToDoListIds()) {
            if(!currentToDoListIds.contains(updatedListId)){
                ToDoList listToAdd = listRepository.getToDoListById(updatedListId);
                folderToBePatched.addToDoList(listToAdd);
            }
        }
        folderRepository.save(folderToBePatched);
        return convertToDTO(folderToBePatched);
    }
    
    public List<FolderDTO> getAllFolders(Integer userId) {
        
        List<Folder> allFoldersByOwnerId = folderRepository.getAllFoldersByOwnerId(userId);
        
        return convertToDTOs(allFoldersByOwnerId);
    }
    
    FolderDTO convertToDTO(Folder folderToConvert) {
        FolderDTO folderDTO = new FolderDTO();
        folderDTO.setId(folderToConvert.getId());
        folderDTO.setName(folderToConvert.getName());
        folderDTO.setOrder(folderToConvert.getOrder());
        
        for (ListInFolder listInFolder : folderToConvert.getToDoLists()) {
            ToDoListDTO toDoListDTO = new ToDoListDTO();
            ToDoList toDoList = listInFolder.getToDoList();
            toDoListDTO.setId(toDoList.getId());
            toDoListDTO.setName(toDoList.getName());
            toDoListDTO.setOrder(listInFolder.getListOrder());
            toDoListDTO.setUserId(toDoList.getCreatedByUser().getId());
            
            folderDTO.getToDoLists().add(toDoListDTO);
        }
        folderDTO.getToDoLists().sort(Comparator.comparing(ToDoListDTO::getOrder));
        
        return folderDTO;
    }
    
    private List<FolderDTO> convertToDTOs(List<Folder> folders) {
        List<FolderDTO> folderDTOs = new ArrayList<>();
        
        for (Folder folder : folders) {
            FolderDTO folderDTO = convertToDTO(folder);
            folderDTOs.add(folderDTO);
        }
        folderDTOs.sort(Comparator.comparing(FolderDTO::getOrder));
        return folderDTOs;
    }
}
