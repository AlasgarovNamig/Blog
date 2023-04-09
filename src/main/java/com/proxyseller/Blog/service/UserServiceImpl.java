package com.proxyseller.Blog.service;

import com.proxyseller.Blog.dto.request.SignUpDto;
import com.proxyseller.Blog.dto.request.UserUpdateRequestDto;
import com.proxyseller.Blog.entity.User;
import com.proxyseller.Blog.exception.InvalidStateException;
import com.proxyseller.Blog.exception.UsernameAlreadyUsedException;
import com.proxyseller.Blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> GetAll() {
        return userRepository.findAll();
    }

    @Override
    public User GetById(String id) {
        return null;
    }

    @Override
    public void Create(SignUpDto obj) {
        userRepository.findByUsername(obj.getUsername()).ifPresent(user -> {
            throw new UsernameAlreadyUsedException(user.getUsername());
        });
        userRepository.save(
                User.builder()
                        .username(obj.getUsername())
                        .password(passwordEncoder.encode(obj.getPassword()))
                        .build()
        );
    }

    /**
     * @author Namig Alasgarov
     * <p>
     * How to work Update User api
     * <p>
     * Get User with token if don`t have a user  this is danger process
     * then check update  username have a database because can not be 2 same username
     * if  don`t have want to user updated username
     * updated user information
     */
    @Override
    public void Update(UserUpdateRequestDto obj) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(authentication.getPrincipal().toString())
                .orElseThrow(() ->
                        new InvalidStateException("Invalid Request Data !!!!")
                );
        userRepository.findByUsername(obj.getUsername()).ifPresent(usr -> {
            if (!(usr.getUsername().equals(user.getUsername()))) {
                throw new UsernameAlreadyUsedException(usr.getUsername());
            }

        });
        user.setUsername(obj.getUsername());
        user.setPassword(passwordEncoder.encode(obj.getPassword()));
        userRepository.save(user);
    }

    /**
     * @author Namig Alasgarov
     * <p>
     * How to work Delete User api
     * <p>
     * Get User with token if don`t have a user  this is danger process
     * then check deleted  user id  same to get with token user id
     * if  same deleted user , this is don`t valid process
     */
    @Override
    public void Delete(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(authentication.getPrincipal().toString())
                .orElseThrow(() -> new InvalidStateException("Invalid Request Data !!!!"));
        if (user.getId().equals(id)) {
            userRepository.deleteById(id);
        } else {
            throw new InvalidStateException("Invalid Request Data !!!!");
        }
    }
}
