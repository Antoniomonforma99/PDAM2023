package com.monteroantonio.PDAM23.security.user.service;

import com.monteroantonio.PDAM23.error.exception.InvalidPasswordException;
import com.monteroantonio.PDAM23.security.user.Roles;
import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.security.user.dto.ChangePasswordRequestDTO;
import com.monteroantonio.PDAM23.security.user.dto.CreateUserRequestDTO;
import com.monteroantonio.PDAM23.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public User createUser(CreateUserRequestDTO createUserRequestDTO, Set<Roles> roles) {
        User user = User.builder()
                .name(createUserRequestDTO.getName())
                .username(createUserRequestDTO.getEmail())
                .email(createUserRequestDTO.getEmail())
                .password(passwordEncoder.encode(createUserRequestDTO.getPassword()))
                .tlf(createUserRequestDTO.getTlf())
                .roles(roles)
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(user);
    }

    public User createUserWithAdminRole(CreateUserRequestDTO createUserRequestDTO) {
        return createUser(createUserRequestDTO, Set.of(Roles.ADMIN));
    }

    public User createUserWithUserRole(CreateUserRequestDTO createUserRequestDTO) {
        return createUser(createUserRequestDTO, Set.of(Roles.USER));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<User> edit (User edited) {
        return repository.findById(edited.getId())
                .map(user -> {
                    user.setTlf(edited.getTlf());
                    return repository.save(user);
                }).or(() -> Optional.empty());
    }

    public Optional<User> findByUsername(String username) {
        return repository.findFirstByUsername(username);
    }

    public User editPassword(User user, ChangePasswordRequestDTO changePasswordRequest) {
        if(!passwordMatch(user, changePasswordRequest.getOldPassword()))
            throw new InvalidPasswordException();
        return repository.findById(user.getId())
                .map(u -> {
                    u.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                    return repository.save(u);
                }).orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    public void delete(User user) {
        repository.deleteById(user.getId());
    }

    public boolean passwordMatch(User user, String clearPassword) {
        return passwordEncoder.matches(clearPassword, user.getPassword());
    }


}
