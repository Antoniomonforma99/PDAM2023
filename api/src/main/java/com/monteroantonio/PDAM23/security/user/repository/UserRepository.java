package com.monteroantonio.PDAM23.security.user.repository;

import com.monteroantonio.PDAM23.security.user.User;
import com.monteroantonio.PDAM23.security.user.dto.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByUsername(String email);




    /*
    @Query(
            """
            SELECT ue.id, ue.created_at, ue.email,
            ue.last_password_change_at,
            ue.name, ue.tlf

            FROM user_entity AS ue
            JOIN user_roles AS ur ON ue.id=ur.user_id
            WHERE UR.ROLES=0
            """
    )
    List<?> findAllAdminUsers();

     */






}
