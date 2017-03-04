package com.newSoftMex.controller;

import com.newSoftMex.model.Comment;
import com.newSoftMex.model.Response;
import com.newSoftMex.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alan.flores on 2/25/17.
 */
@RestController
@RequestMapping("/private/api/v1")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/getCommentsByUserService/{username}", method = RequestMethod.GET)
    public Response<List<Comment>> finishContractByAdmin(@PathVariable("username") String username){
        return commentService.getCommentsByUserService(username);
    }

    @RequestMapping(value = "/createComment/{comment}/{username}/{usernameService}", method = RequestMethod.POST)
    public Response<Comment> createComment(@PathVariable("comment") String comment,
                                                 @PathVariable("username") String username,
                                                 @PathVariable("usernameService") String usernameService){
        return commentService.createComment(comment, username, usernameService);
    }
}
