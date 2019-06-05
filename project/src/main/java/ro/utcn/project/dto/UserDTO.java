package ro.utcn.project.dto;

import lombok.Data;
import ro.utcn.project.entity.User;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String type;

    public static UserDTO ofEntity(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setType(user.getType());
        return userDTO;
    }
}
