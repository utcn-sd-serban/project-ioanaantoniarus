package ro.utcn.project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.project.entity.Genre;
import ro.utcn.project.repository.api.GenreRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcGenreRepository implements GenreRepository {
    private final JdbcTemplate template;

    @Override
    public Genre save(Genre genre) {
        if(genre.getId()!= 0){
            update(genre);
        }
        else{
            genre.setId(insert(genre));
        }
        return genre;
    }

    public int insert(Genre genre) {
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("genre");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("name", genre.getName());
        return insert.executeAndReturnKey(data).intValue();
    }

    @Override
    public void delete(int id) {
        template.update("DELETE FROM tag WHERE id= ?",id);
    }

    @Override
    public List<Genre> findAll() {
        return template.query("SELECT * FROM genre", new GenreMapper());
    }

    @Override
    public void update(Genre genre) {
        template.update("UPDATE FROM genre SET name =? WHERE id= ?",
                genre.getName(),genre.getId());
    }

    @Override
    public Genre findByName(String name) {
        List<Genre> genres=template.query("SELECT * FROM genre WHERE name= ?",
                new Object[]{ name },
                new GenreMapper());
        return genres.isEmpty() ? null : genres.get(0);
    }
}
