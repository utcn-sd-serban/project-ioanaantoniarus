package ro.utcn.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.project.entity.Genre;
import ro.utcn.project.exception.GenreNotFoundException;
import ro.utcn.project.repository.api.RepositoryFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Genre> listGenres(){
        return repositoryFactory.createGenreRepository().findAll();
    }

    @Transactional
    public Genre addGenre(String name){
        Genre genre=repositoryFactory.createGenreRepository().findByName(name);
        if(genre!=null){
            return genre;
        }
        else{
            return repositoryFactory.createGenreRepository().save(new Genre(name));
        }
    }

}
