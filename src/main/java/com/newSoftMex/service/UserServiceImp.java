package com.newSoftMex.service;

import com.newSoftMex.model.Response;
import com.newSoftMex.model.User;
import com.newSoftMex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public Response<User> registerUser(String name,String lastname,String email,String password,int type){
        Response<User> response = new Response<>();

        try {
            User user = new User(name,lastname,email,password);
            response.setData(user);
            userRepository.save(user);
            response.setErrorMessage(null);
            response.setMessage("The user was register successfully.");
            response.setSuccess(true);
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
        }

        return response;
    }

    @Override
    public Response<List<User>> allUser(){
        Response<List<User>> response = new Response<>();

        try {
            response.setData(userRepository.findAll());
            response.setErrorMessage(null);
            response.setMessage("They are all the users.");
            response.setSuccess(true);
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
        }

        return response;
    }
}
