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
    
    //ToDo: change method to a Hibernate default (don't use queries).
    @Query(value = "select * from folder where folder.id = ?1")
    Folder findFolderById(@RequestParam Integer id);
}
