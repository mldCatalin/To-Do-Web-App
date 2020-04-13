package mldcatalinprojects.wunderlist.repository;


import mldcatalinprojects.wunderlist.model.Folder;
import mldcatalinprojects.wunderlist.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface FolderRepository extends CrudRepository<Folder, Integer> {
    
    @Query(value = "select count(id) from folder where folder.user_id = ?1", nativeQuery = true)
    Integer countFolderByUserId(@RequestParam Integer userId);
}
