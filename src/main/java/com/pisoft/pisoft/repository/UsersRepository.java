package com.pisoft.pisoft.repository;

import com.pisoft.pisoft.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users , Long> {

    Optional<Users> findByEmail(String username);
}
