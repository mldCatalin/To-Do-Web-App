package mldcatalinprojects.wunderlist.controller;

import mldcatalinprojects.wunderlist.model.Folder;
import mldcatalinprojects.wunderlist.model.FolderDTO;
import mldcatalinprojects.wunderlist.model.User;
import mldcatalinprojects.wunderlist.model.UserDTO;
import mldcatalinprojects.wunderlist.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/folders")
public class FolderController {
    
//    private FolderService folderService;
//
//    private FolderController(@Autowired FolderService folderService){
//        this.folderService = folderService;
//    }
//
//    @PostMapping
//    public @ResponseBody Folder addFolder(@RequestBody FolderDTO newFolder) throws Exception {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        return folderService.addFolder(newFolder);
//    }
    
    
}
