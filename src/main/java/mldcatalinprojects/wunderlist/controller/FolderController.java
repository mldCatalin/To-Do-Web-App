package mldcatalinprojects.wunderlist.controller;

import mldcatalinprojects.wunderlist.model.FolderDTO;
import mldcatalinprojects.wunderlist.service.FolderService;
import mldcatalinprojects.wunderlist.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/folders")
public class FolderController {
    
    private FolderService folderService;
    private ListService listService;
    
    public FolderController(@Autowired FolderService folderService, @Autowired ListService listService) {
        this.folderService = folderService;
        this.listService = listService;
    }
    
    @GetMapping
    public @ResponseBody List<FolderDTO> getFolders(@RequestBody Map<String, Integer> body) {
        return folderService.getAllFolders(body.get("userId"));
    }
    
    @PostMapping(path = "/new")
    public @ResponseBody FolderDTO createFolder(@RequestBody Map<String, Integer> requestBody){
        
        return folderService.createNewFolder(requestBody);
    }
    
}
