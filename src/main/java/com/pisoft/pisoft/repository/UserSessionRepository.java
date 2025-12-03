package com.pisoft.pisoft.repository;

import com.pisoft.pisoft.entity.UserSession;
import com.pisoft.pisoft.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    List<UserSession> findByUsers(Users users);

    Optional<UserSession> findByRefreshToken(String refreshToken);
}
