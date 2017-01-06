package com.newSoftMex.controller;

import com.newSoftMex.model.Response;
import com.newSoftMex.model.User;
import com.newSoftMex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    //curl -X POST http://localhost:8080/api_job/user/registerUser/alan/flores/alan.flores/12345/elchidoclear
    @RequestMapping(value = "/registerUser/{name}/{lastname}/{email}/{password}/{type}", method = RequestMethod.POST)
    public Response registerUser(@PathVariable("name") String name,
                                 @PathVariable("lastname") String lastname,
                                 @PathVariable("email") String email,
                                 @PathVariable("password") String password,
                                 @PathVariable("type") int type){
        return userService.registerUser(name, lastname, email, password, type);
    }

    @RequestMapping(value = "/admin/getUser", method = RequestMethod.GET)
    public Response<List<User>> getAllUser() {

        return userService.allUser();

    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Response<List<User>> getAllUser2() {

        return userService.allUser();

    }
}
