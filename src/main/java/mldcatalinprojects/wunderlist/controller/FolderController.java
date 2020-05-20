package mldcatalinprojects.wunderlist.controller;

import mldcatalinprojects.wunderlist.model.FolderDTO;
import mldcatalinprojects.wunderlist.model.UpdatedIds;
import mldcatalinprojects.wunderlist.repository.FolderRepository;
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
    
    public FolderController(@Autowired FolderService folderService) {
        this.folderService = folderService;
    }
    
    @GetMapping
    public @ResponseBody List<FolderDTO> getFolders(@RequestBody Map<String, Integer> body) {
        
        return folderService.getAllFolders(body.get("userId"));
    }
    
    
    
    
    //ToDo: Method not final. needs more testing and development before implementation
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public @ResponseBody FolderDTO patchFolder(@PathVariable("id") Integer id, @RequestBody UpdatedIds updatedIds) {
        
        return folderService.patchFolder(id, updatedIds);
    }
}