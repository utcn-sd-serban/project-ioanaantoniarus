package ro.utcn.project.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.project.dto.UserDTO;
import ro.utcn.project.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserDTO readCurrentUser(){
        return userService.loadCurrentUserDTO();
    }
}
