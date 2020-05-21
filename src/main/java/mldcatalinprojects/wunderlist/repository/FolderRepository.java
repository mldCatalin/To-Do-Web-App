package mldcatalinprojects.wunderlist.repository;

import mldcatalinprojects.wunderlist.model.Folder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FolderRepository extends CrudRepository<Folder, Integer> {
    
    @Query(value = "select count(id) from folder where folder.user_id = ?1", nativeQuery = true)
    Integer countFolderByUserId(@RequestParam Integer userId);
    
    List<Folder> getAllFoldersByOwnerId(@RequestParam Integer ownerId);
    
    Folder findFolderById(@RequestParam Integer id);
    
    
    
    //ToDo: possible issues for shared list. Same listId will be found in multiple folders
    @Query(value = "select folder.* from folder " +
            "left join list_in_folder as lf on folder.id = lf.folder_id " +
            "where (lf.to_do_list_id = ?1)", nativeQuery = true)
    Folder getToDoListContainingFolder(@RequestParam Integer id);
    
    Folder getFolderById(Integer id);
    
    
}
