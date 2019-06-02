package ro.utcn.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }
}
