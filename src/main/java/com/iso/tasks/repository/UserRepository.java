package com.iso.tasks.repository;

import com.iso.tasks.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("select u.id from users u where u.username = ?1")
    long getId(String username);
}
