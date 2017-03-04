package com.newSoftMex.repository;

import com.newSoftMex.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alan.flores on 1/6/17.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("select c from Comment c where c.idUserServiceCommented.username=:username")
    List<Comment> getCommentsByUserService(@Param("username")String username);
}
