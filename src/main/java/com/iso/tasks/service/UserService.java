package com.iso.tasks.service;

import com.iso.tasks.model.User;
import com.iso.tasks.model.dto.UserCreateDTO;
import com.iso.tasks.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Long getUserId(String username) {
        return userRepository.getId(username);
    }

    public boolean createUser(UserCreateDTO userCreateDTO) {
        final var username = userCreateDTO.getUsername();
        final Optional<User> dbUser = userRepository.findByUsername(username);

        if (dbUser.isPresent()) {
            return false;
        }

        final String password = encoder.encode(userCreateDTO.getPassword());

        final User newUser = User.builder()
                .username(username)
                .password(password)
                .roles("USER")
                .build();

        userRepository.save(newUser);

        return true;
    }
}
