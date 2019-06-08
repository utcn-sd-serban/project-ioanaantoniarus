package ro.utcn.project.repository.api;

import ro.utcn.project.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    void delete(int id);

    void update(Genre genre);

    Genre findByName(String name);

    Genre save(Genre genre);

    List<Genre> findAll();

}
