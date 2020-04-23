package mldcatalinprojects.wunderlist.controller;

import mldcatalinprojects.wunderlist.model.ToDoList;
import mldcatalinprojects.wunderlist.model.ToDoListDTO;
import mldcatalinprojects.wunderlist.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/lists")
public class ListController {
    
    private ListService listService;
    
    public ListController(@Autowired ListService listService) {
        this.listService = listService;
    }
    
    @PostMapping
    public @ResponseBody ToDoList addList(@RequestBody ToDoListDTO newList) {
        return listService.addNewList(newList);
    }
    
}
