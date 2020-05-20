package mldcatalinprojects.wunderlist.repository;


import mldcatalinprojects.wunderlist.model.Folder;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public abstract class FolderRepositoryAbstract extends SimpleJpaRepository<Folder, Integer> implements FolderRepository {
    
    
    public FolderRepositoryAbstract(Class<Folder> domainClass, EntityManager em) {
        super(domainClass, em);
    }
    
    @Override
    public Folder save (Folder folder){
        
        
        
        super.save(folder);
        
        return null;
    }
    
}
