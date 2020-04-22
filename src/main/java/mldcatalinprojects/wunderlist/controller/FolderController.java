package mldcatalinprojects.wunderlist.controller;

import mldcatalinprojects.wunderlist.model.FolderDTO;
import mldcatalinprojects.wunderlist.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/folders")
public class FolderController {
    
    private FolderService folderService;
    
    public FolderController(@Autowired FolderService folderService) {
        this.folderService = folderService;
    }
    
    @GetMapping
    public @ResponseBody
    List<FolderDTO> getFolders(@RequestBody Map<String, Integer> body) {
        
        return folderService.getAllFolders(body.get("userId"));
    }
    
}
