package mldcatalinprojects.wunderlist.controller;

import mldcatalinprojects.wunderlist.model.FolderDTO;
import mldcatalinprojects.wunderlist.model.ToDoList;
import mldcatalinprojects.wunderlist.model.ToDoListDTO;
import mldcatalinprojects.wunderlist.model.UpdatedIds;
import mldcatalinprojects.wunderlist.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "/lists")
public class ListController {
    
    private ListService listService;
    
    public ListController(@Autowired ListService listService) {
        this.listService = listService;
    }
    
    
    //TODO: change return type to DTO
    @PostMapping
    public @ResponseBody
    ToDoList addList(@RequestBody ToDoListDTO newList) {
        return listService.addNewList(newList);
    }
    
    
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public @ResponseBody
    FolderDTO moveToDoList(@PathVariable("id") Integer id, @RequestBody Map<String, Integer> body) {
        
        return listService.moveToDoList(id, body.get("newFolderId"));
    }
}
