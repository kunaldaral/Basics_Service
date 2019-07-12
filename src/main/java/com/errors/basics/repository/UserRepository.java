package com.errors.basics.repository;

import com.errors.basics.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u where u.firstName=(:firstName)")
    List<User> searchByName(String firstName);
}
