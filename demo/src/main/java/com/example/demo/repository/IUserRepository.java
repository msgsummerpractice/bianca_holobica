package com.example.demo.repository;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    List<User> findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(String username);
    
    @Query("SELECT COUNT(u) FROM User u")
    long countUsers();
}
