package com.newSoftMex.service;

import com.newSoftMex.model.Response;
import com.newSoftMex.model.User;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
public interface UserService {
    Response<User> registerUser(String name,String lastname,String email,String password,int type);

    Response<List<User>> allUser();
}