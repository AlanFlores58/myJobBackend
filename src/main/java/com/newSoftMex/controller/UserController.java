package com.newSoftMex.controller;

import com.newSoftMex.model.Credentials;
import com.newSoftMex.model.Cupon;
import com.newSoftMex.model.Response;
import com.newSoftMex.model.User;
import com.newSoftMex.security.JWTAuthenticationToken;
import com.newSoftMex.service.CuponService;
import com.newSoftMex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@RestController
@RequestMapping("/private/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CuponService cuponService;

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public Response<Credentials> getCurrentUser(JWTAuthenticationToken authentication){
        return userService.getCurrentUser((String) authentication.getCredentials());
    }

    @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET)
    public Response<User> getUserInfo(@PathVariable("id") Long id){
        return userService.getUserInfo(id);
    }

    @RequestMapping(value = "/admin/getUser", method = RequestMethod.GET)
    public Response<List<User>> getAllUser() {
        return userService.allUser();
    }


    @RequestMapping(value = "/filter/getUser", method = RequestMethod.GET)
    public Response<List<User>> getAllUser2() {
        return userService.allUser();
    }

    @RequestMapping(value = "/getTicketsByUser/{username}", method = RequestMethod.GET)
    public Response<List<Cupon>> getTicketsByUser(@PathVariable("username") String username){
        return cuponService.getTicketsByUser(username);
    }

    @RequestMapping(value = "/getTicketsByUserNotUsed/{username}", method = RequestMethod.GET)
    public Response<List<Cupon>> getTicketsByUserNotUsed(@PathVariable("username") String username){
        return cuponService.getTicketsByUserNotUsed(username);
    }

    @RequestMapping(value = "/getPremium/{username}", method = RequestMethod.POST)
    public Response<Credentials> getPremium(@PathVariable("username") String username){
        return userService.getPremium(username);
    }

    @RequestMapping(value = "/unUpgradePremium/{username}", method = RequestMethod.POST)
    public Response<User> unUpgradePremium(@PathVariable("username") String username){
        return userService.unUpgradePremium(username);
    }

}
