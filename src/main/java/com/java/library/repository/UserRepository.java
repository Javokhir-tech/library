package com.java.library.repository;

import com.java.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author abdullaevdjavokhir@gmail.com
 * @created 15/01/2022 - 1:36 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);
}
