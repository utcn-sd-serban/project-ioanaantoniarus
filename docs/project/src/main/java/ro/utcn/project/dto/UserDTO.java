package ro.utcn.project.dto;

import lombok.Data;
import ro.utcn.project.entity.User;

@Data
public class UserDTO {
    private String username;
    private String password;

    public static UserDTO ofEntity(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
