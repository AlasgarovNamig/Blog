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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public void Update(UserUpdateRequestDto obj) {
        userRepository.findByUsername(obj.getUsername()).ifPresent(user -> {
            throw new UsernameAlreadyUsedException(user.getUsername());
        });
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals(obj.getId())) {
            var user = userRepository.findByUsername(obj.getUsername())
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    String.format("User %s was not found in the database", obj.getUsername())));
            user.setPassword(passwordEncoder.encode(obj.getPassword()));
            userRepository.save(user);
        } else {
            throw new InvalidStateException("Invalid Request Data !!!!");
        }
    }

    @Override
    public void Delete(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals(id)) {
            userRepository.deleteById(id);
        } else {
            throw new InvalidStateException("Invalid Request Data !!!!");
        }
    }
}
