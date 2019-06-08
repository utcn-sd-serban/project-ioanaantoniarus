package ro.utcn.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.project.dto.UserDTO;
import ro.utcn.project.entity.User;
import ro.utcn.project.exception.UserNotFoundException;
import ro.utcn.project.repository.api.RepositoryFactory;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public User login(String username, String password){
        return repositoryFactory.createUserRepository().validateUser(username,password);
    }

    @Transactional
    public User createProfile(String username, String password){
        return repositoryFactory.createUserRepository().save(new User(username,password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repositoryFactory.createUserRepository().findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Unknown user!"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Transactional
    public UserDTO loadCurrentUserDTO(){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=repositoryFactory.createUserRepository().findByUsername(name).orElseThrow(UserNotFoundException::new);
        return UserDTO.ofEntity(user);
    }

    @Transactional
    public User loadCurrentUser(){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        return repositoryFactory.createUserRepository().findByUsername(name).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public String getUserType(){
        User user=loadCurrentUser();
        return repositoryFactory.createUserRepository().getUserType(user);
    }
}
