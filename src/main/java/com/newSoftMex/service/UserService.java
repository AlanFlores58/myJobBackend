package com.newSoftMex.service;

import com.newSoftMex.model.Credentials;
import com.newSoftMex.model.Response;
import com.newSoftMex.model.User;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
public interface UserService {
    Response<User> registerUser(String name, String lastname,  String email, String username, String password, Long type, Long sex, String url, String image, String cellphone, String telephone);

    Response<List<User>> allUser();

    Response<Credentials> enableUser(String username);

    Response<Credentials> login(Credentials  credentials);

    Response<Credentials> getCurrentUser(String token);

    Response<User> getUserInfo(Long id);

    Response<Credentials> getPremium(String username);

    Response<User> unUpgradePremium(String username);
}