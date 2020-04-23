package mldcatalinprojects.wunderlist.service;

import mldcatalinprojects.wunderlist.model.*;
import mldcatalinprojects.wunderlist.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class FolderService {
    
    private FolderRepository folderRepository;
    
    public FolderService(@Autowired FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }
    
    public List<FolderDTO> getAllFolders(Integer userId) {
    
        List<Folder> allFoldersByOwnerId = folderRepository.getAllFoldersByOwnerId(userId);
    
        return convertToDTOs(allFoldersByOwnerId);
    }
    
    public FolderDTO createNewFolder(Map<String, Integer> requestBody) {
        return null;
    }
    
    private List<FolderDTO> convertToDTOs(List<Folder> folders) {
        List<FolderDTO> folderDTOs = new ArrayList<>();
        
        for (Folder folder : folders) {
            FolderDTO folderDTO = new FolderDTO();
            folderDTO.setId(folder.getId());
            folderDTO.setName(folder.getName());
            folderDTO.setOrder(folder.getOrder());
    
            for (ListInFolder listInFolder : folder.getToDoLists()) {
                ToDoListDTO toDoListDTO = new ToDoListDTO();
                ToDoList toDoList = listInFolder.getToDoList();
                toDoListDTO.setId(toDoList.getId());
                toDoListDTO.setName(toDoList.getName());
                toDoListDTO.setOrder(listInFolder.getListOrder());
                toDoListDTO.setUserId(toDoList.getCreatedByUser().getId());
                
                folderDTO.getToDoLists().add(toDoListDTO);
            }
            folderDTO.getToDoLists().sort(Comparator.comparing(ToDoListDTO::getOrder));
            folderDTOs.add(folderDTO);
        }
        folderDTOs.sort(Comparator.comparing(FolderDTO::getOrder));
        return folderDTOs;
    }
}
