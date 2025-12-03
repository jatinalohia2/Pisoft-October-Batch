package com.pisoft.pisoft.service;

import com.pisoft.pisoft.dto.SignUpDto;
import com.pisoft.pisoft.dto.UsersDto;
import com.pisoft.pisoft.entity.Users;
import com.pisoft.pisoft.exception.ResourceNotFound;
import com.pisoft.pisoft.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username)
                .orElseThrow(() -> new AuthenticationException("User not found with email : " + username) {
                });

    }

    public UsersDto signUp(SignUpDto signUpDto) {

        Optional<Users> byEmail = usersRepository.findByEmail(signUpDto.getEmail());

        if (byEmail.isPresent()){
            throw new RuntimeException("User already exist with email : "+signUpDto.getEmail());
        }

        Users users = modelMapper.map(signUpDto, Users.class);
        users.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Users savedUsers = usersRepository.save(users);
        return modelMapper.map(savedUsers , UsersDto.class);
    }

    public Users findById(Long userId) {
        return usersRepository.findById(userId).orElseThrow(()->
                new ResourceNotFound("User not found with id : "+userId));
    }

    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }

    public Users save(Users users) {
        return usersRepository.save(users);
    }
}
