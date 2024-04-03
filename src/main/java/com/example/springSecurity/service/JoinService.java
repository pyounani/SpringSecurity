package com.example.springSecurity.service;

import com.example.springSecurity.dto.JoinDTO;
import com.example.springSecurity.entity.UserEntity;
import com.example.springSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        // db에 이미 동일한 username을 가진 회원이 존재하는지?
        Optional<UserEntity> findUser = userRepository.findByUsername(joinDTO.getUsername());
        if (findUser.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(joinDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        user.setRole("ROLE_USER");    // ROLE_ADMIN

        userRepository.save(user);

    }


}
