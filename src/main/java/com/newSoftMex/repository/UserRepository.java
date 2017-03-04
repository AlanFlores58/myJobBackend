package com.newSoftMex.repository;

import com.newSoftMex.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u where u.username=:username")
    User findByUserName(@Param("username") String username);

    @Query("Select u from User u where u.username=:username and u.password=:password and u.enabled=true")
    User authenticateUser(@Param("username") String username,@Param("password") String password);
}
