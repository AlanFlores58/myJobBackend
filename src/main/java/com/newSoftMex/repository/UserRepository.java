package com.newSoftMex.repository;

import com.newSoftMex.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
