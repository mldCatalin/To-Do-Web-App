package mldcatalinprojects.wunderlist.service;

import mldcatalinprojects.wunderlist.model.*;
import mldcatalinprojects.wunderlist.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FolderService {
    
    private FolderRepository folderRepository;
    
    public FolderService(@Autowired FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    
    public FolderDTO patchFolder(Integer id, PatchFolderInstructor patchFolderInstructor) {
        Folder folderToBePatched = folderRepository.findFolderById(id);
        if (folderToBePatched.getToDoLists().size() > patchFolderInstructor.getToDoListIds().size()) {
            Folder updatedFolder = cleanFolder(folderToBePatched, patchFolderInstructor);
            return convertToDTO(updatedFolder);
        } else {
            Folder updatedFolder = enrichFolder(folderToBePatched, patchFolderInstructor);
            return convertToDTO(updatedFolder);
        }
    }
    
    private Folder cleanFolder(Folder folderToBePatched, PatchFolderInstructor updatedListIds) {
        List<Integer> currentToDoListIds = folderToBePatched.getToListIds();
        
        for (Integer existingId : currentToDoListIds) {
            if (!updatedListIds.getToDoListIds().contains(existingId)) {
                ToDoList listToRemove = folderToBePatched.getToDoListById(existingId);
                folderToBePatched.removeToDoLIst(listToRemove);
            }
        }
        return folderToBePatched;
    }
    
    private Folder enrichFolder(Folder folderToBePatched, PatchFolderInstructor updatedListIds) {
        
        
        return folderToBePatched;
    }
    
    public List<FolderDTO> getAllFolders(Integer userId) {
        
        List<Folder> allFoldersByOwnerId = folderRepository.getAllFoldersByOwnerId(userId);
        
        return convertToDTOs(allFoldersByOwnerId);
    }
    
    private FolderDTO convertToDTO(Folder folderToConvert) {
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
