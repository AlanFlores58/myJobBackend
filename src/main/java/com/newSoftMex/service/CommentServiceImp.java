package com.newSoftMex.service;

import com.newSoftMex.model.Comment;
import com.newSoftMex.model.Response;
import com.newSoftMex.model.User;
import com.newSoftMex.repository.CommentRepository;
import com.newSoftMex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Service("commentService")
public class CommentServiceImp implements CommentService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Response<Comment> createComment(String commentString, String username, String usernameService){
        Response<Comment> response = new Response<>();
        try {
            Comment comment = new Comment();
            comment.setComment(commentString.replace("%20", " "));
            comment.setUserDoer(userRepository.findByUserName(username));
            comment.setIdUserServiceCommented(userRepository.findByUserName(usernameService));
            response.setData(commentRepository.save(comment));
            response.setErrorMessage(null);
            response.setMessage("User commented " + username);
            response.setSuccess(true);
            response.setStatus("200");

        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
        }

        return response;
    }

    @Override
    public Response<List<Comment>> getCommentsByUserService(String username){
        Response<List<Comment>> response = new Response<>();
        try {
            response.setData(commentRepository.getCommentsByUserService(username));
            response.setErrorMessage(null);
            response.setMessage("User commented " + username);
            response.setSuccess(true);
            response.setStatus("200");

        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
        }

        return response;
    }
}
