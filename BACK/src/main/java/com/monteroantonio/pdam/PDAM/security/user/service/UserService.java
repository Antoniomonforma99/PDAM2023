package com.monteroantonio.pdam.PDAM.security.user.service;

import com.monteroantonio.pdam.PDAM.error.exception.InvalidPasswordException;
import com.monteroantonio.pdam.PDAM.security.user.Roles;
import com.monteroantonio.pdam.PDAM.security.user.User;
import com.monteroantonio.pdam.PDAM.security.user.dto.ChangePasswordRequestDTO;
import com.monteroantonio.pdam.PDAM.security.user.dto.CreateUserRequestDTO;
import com.monteroantonio.pdam.PDAM.security.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
                .email(createUserRequestDTO.getEmail())
                .password(passwordEncoder.encode(createUserRequestDTO.getPassword()))
                .tlf(createUserRequestDTO.getTlf())
                .roles(roles)
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

    public Optional<User> findByEmail(String email) {
        return repository.findFirstByEmail(email);
    }

    public User editPassword(User user, ChangePasswordRequestDTO changePasswordRequestDTO){
        if (!user.getPassword().matches(changePasswordRequestDTO.getOldPassword()))
            throw new InvalidPasswordException();
        return repository.findById(user.getId())
                .map(u -> {
                    u.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));
                    return repository.save(u);
                }).orElseThrow(() -> new EntityNotFoundException());
    }

    @Transactional
    public void delete(User user) {
        repository.deleteById(user.getId());
    }


}
