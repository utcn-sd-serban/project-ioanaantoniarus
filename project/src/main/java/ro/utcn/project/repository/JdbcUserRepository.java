package ro.utcn.project.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.project.entity.User;
import ro.utcn.project.repository.api.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate template;

    @Override
    public List<User> findAll() {
        List<User> users=template.query("SELECT * FROM user", new UserMapper());
        return users;
    }

    @Override
    public Optional<User> findById(int id) {
        List<User> users=template.query("SELECT * FROM user WHERE id= ?",
                new Object[]{ id },
                new UserMapper());
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public User save(User user) {
        if(user.getId()!= 0){
            update(user);
        }
        else{
            user.setId(insert(user));
        }
        return user;
    }

    @Override
    public void delete(int id) {
        template.update("DELETE FROM user WHERE id=?",id);
    }

    @Override
    public void update(User user) {
        template.update("UPDATE user SET username =?, password=?, type=? WHERE id= ?",
                user.getUsername(),user.getPassword(),user.getType(),user.getId());
    }

    @Override
    public User validateUser(String username, String password) {
        List<User> users=template.query("SELECT * FROM  user WHERE username= ? and password= ?",new Object[]{username,password},new UserMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users=template.query("SELECT * FROM user WHERE username= ?",
                new Object[]{ username },
                new UserMapper());
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public String getUserType(User user) {
        List<User> users=template.query("SELECT * FROM user WHERE id=?",new Object[]{user.getId()},new UserMapper());
        return users.get(0).getType();
    }

    private int insert(User user){
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("user");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("username", user.getUsername());
        data.put("password",user.getPassword());
        data.put("type",user.getType());
        return insert.executeAndReturnKey(data).intValue();
    }
}
