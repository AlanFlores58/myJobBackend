package com.newSoftMex.controller;

import com.newSoftMex.model.Credentials;
import com.newSoftMex.model.Response;
import com.newSoftMex.model.User;
import com.newSoftMex.service.ServiceService;
import com.newSoftMex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alan.flores on 1/11/17.
 */

@RestController
@RequestMapping("/public/api/v1")
public class PublicController {

    @Autowired
    UserService userService;

    //curl -X GET  -d '{"username": "alan_flores","password": "123456"}' -H "Content-Type: application/json" http://localhost:8080/api_job/public/api/v1/login
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces="application/json", consumes="application/json")
    public Response login(@RequestBody Credentials credentials){
        return userService.login(credentials);
    }

    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET)
    public Response login(@PathVariable("username") String username,
                          @PathVariable("password") String password){
        return userService.login(new Credentials(username,password));
    }

    //curl -X POST http://localhost:8080/api_job/public/api/v1/signup/juan/perez/alan.isaim58@gmail.com/nuevo_alan/1234567/2/1
    @RequestMapping(value = "/signup/{name}/{lastname}/{email}/{username}/{password}/{type}/{sex}/{image}/{cellphone}/{telephone}", method = RequestMethod.POST)
    public Response<User> registerUser(@PathVariable("name") String name,
                                       @PathVariable("lastname") String lastname,
                                       @PathVariable("email") String email,
                                       @PathVariable("username") String username,
                                       @PathVariable("password") String password,
                                       @PathVariable("type") Long type,
                                       @PathVariable("sex") Long sex,
                                       @PathVariable("image") String image,
                                       @PathVariable("cellphone") String cellphone,
                                       @PathVariable("telephone") String telephone){
        return userService.registerUser(name, lastname, email, username, password,  type, sex, "http://localhost:8090/front_job/enableUser", image, cellphone, telephone);
    }

    //
    @RequestMapping(value = "/enableUser/{username}", method = RequestMethod.PUT)
    public Response getEnable(@PathVariable("username") String username) {
        return userService.enableUser(username);
    }





    ///test

    @Autowired
    ServiceService serviceService;

    @RequestMapping(value = "/getServicesByUser/{username}", method = RequestMethod.GET)
    public Response getServicesByUser(@PathVariable("username") String username) {
        return serviceService.getServicesByUser(username);
    }

    @RequestMapping(value = "/getServicesByUserAndId/{username}/{serviceID}", method = RequestMethod.GET)
    public Response getServicesByUserAndId(@PathVariable("username") String username,@PathVariable("serviceID") Long serviceID) {
        return serviceService.getServicesByUserAndServiceID(username, serviceID);
    }
}
