package com.newSoftMex.service;

import com.newSoftMex.model.Comment;
import com.newSoftMex.model.Response;

import java.util.List;

/**
 * Created by alan.flores on 2/25/17.
 */
public interface CommentService {
    Response<Comment> createComment(String commentString, String username, String usernameService);

    Response<List<Comment>> getCommentsByUserService(String username);
}
