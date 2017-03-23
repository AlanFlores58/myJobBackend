package com.newSoftMex.service;

import com.newSoftMex.model.*;
import com.newSoftMex.repository.CupoRepository;
import com.newSoftMex.repository.UserRepository;
import com.newSoftMex.repository.UserRoleRepository;
import com.newSoftMex.security.EmailAuth;
import com.newSoftMex.security.JWTManager;
import com.sun.mail.smtp.SMTPTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Role;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.Calendar;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.List;

/**
 * Created by alan.flores on 1/3/17.
 */
@Service("userService")
@PropertySource(value = { "classpath:application.properties" })
public class UserServiceImp implements UserService {
    @Autowired
    private Environment environment;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    CupoRepository cupoRepository;


    @Override
    public Response<User> registerUser(String name, String lastname,  String email, String username, String password, Long type, Long sex, String url, String image, String cellphone, String telephone){
        Response<User> response = new Response<>();
        StringBuilder text = new StringBuilder("Somos my job y queremos que autentifique su cuenta aqui: ");
        text.append(url + "?username=" + username.replace(" ","%20"));

        try {
            UserRole userRole = userRoleRepository.findOne(type);
            User user = new User();
            user.setTelephone(telephone);
            user.setCellphone(cellphone);
            user.setName(name.replace("%20"," "));
            user.setLast_name(lastname.replace("%20"," "));
            user.setUsername(username.replace("%20"," "));
            user.setEmail(email.replace("%20"," "));
            user.setPassword(password.replace("%20"," "));
            user.setUserRole(userRole);
            user.setSex(sex);
            user.setImage(image.replace("--","/"));
            response.setStatus("200");
            user.setEnabled(false);
            //send email
            if(sendEmail(email,"Confirma tu cuenta de My Job",text.toString())){
                response.setData(userRepository.save(user));
                response.setErrorMessage(null);
                response.setMessage("The user was register successfully.");
                response.setSuccess(true);
            }else{
                response.setErrorMessage("There are problems with the register email.");
                response.setMessage(null);
                response.setSuccess(false);
                response.setStatus("401");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("401");
        }

        return response;
    }

    @Override
    public Response<Credentials> enableUser(String username){
        Response<Credentials> response = new Response<>();
        JWTManager jwtManager = new JWTManager();
        Credentials credentials = new Credentials();
        try {
            User user = userRepository.findByUserName(username);
            if(user != null){
                user.setEnabled(true);
                userRepository.save(user);
                credentials.setId(user.getId());
                credentials.setUsername(user.getUsername());
                credentials.setRole(user.getUserRole().getName());
                credentials.setToken(jwtManager.createJWT(username,user.getPassword(),user.getUserRole().getName(),user.getId(),1200000,user.getImage(), user.getPremiumDate(), user.getEmail()));
                credentials.setPassword(user.getPassword());
                credentials.setPremiumDate(user.getPremiumDate());
                credentials.setEmail(user.getEmail());
                response.setData(credentials);
                response.setErrorMessage(null);
                response.setMessage("User enable " + username);
                response.setSuccess(true);
            }else {
                response.setData(null);
                response.setErrorMessage("UNAUTHORIZED");
                response.setMessage(null);
                response.setStatus("401");
            }

        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
        }

        return response;
    }

    public Response<Credentials> login(Credentials  credentials){
        Response<Credentials> response = new Response<>();
        JWTManager jwtManager = new JWTManager();
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        try {
            User user = userRepository.authenticateUser(username, password);
            if(user != null && user.isEnabled() ){
                credentials.setId(user.getId());
                credentials.setUsername(user.getUsername());
                credentials.setPassword(user.getPassword());
                credentials.setRole(user.getUserRole().getName());
                credentials.setToken(jwtManager.createJWT(username,user.getPassword(),user.getUserRole().getName(),user.getId(),1200000, user.getImage(), user.getPremiumDate(), user.getEmail()));
                credentials.setPremiumDate(user.getPremiumDate());
                credentials.setEmail(user.getEmail());
                response.setData(credentials);
                response.setErrorMessage(null);
                response.setMessage("Authenticate.");
                response.setStatus("200");
            }else{
                response.setData(null);
                response.setErrorMessage("UNAUTHORIZED");
                response.setMessage(null);
                response.setStatus("401");
            }
            response.setSuccess(true);
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<List<User>> allUser(){
        Response<List<User>> response = new Response<>();

        try {
            /*sendEmail("alan.isaim58@gmail.com","Test","Dear Mail Crawler," +
                    "\n\n No spam to my email, please!");*/
            response.setData(userRepository.findAll());
            response.setErrorMessage(null);
            response.setMessage("They are all the users.");
            response.setSuccess(true);
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            e.printStackTrace();
        }

        return response;
    }


    private boolean sendEmail(String emailTo,String subject, String body) throws javax.mail.MessagingException {

        System.out.println(emailTo);
        Properties props = new Properties();
        String emailFrom = environment.getProperty("email.email");
        String password = environment.getProperty("email.password");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        EmailAuth emailAuth = new EmailAuth();
        emailAuth.setPassword(password);
        emailAuth.setUser(emailFrom);
        Session session = Session.getDefaultInstance(props,emailAuth);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Done");
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response<Credentials> getCurrentUser(String token){
        Response<Credentials> response = new Response<>();
        JWTManager jwtManager = new JWTManager();
        try {
            response.setData(jwtManager.parseJWT(token));
            response.setErrorMessage(null);
            response.setMessage("They are all the users.");
            response.setSuccess(true);
            response.setStatus("200");
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("403");
            e.printStackTrace();
        }

        return response;
    }



    @Override
    public Response<User> getUserInfo(Long id){
        Response<User> response = new Response<>();
        try {
            response.setData(userRepository.findOne(id));
            response.setErrorMessage(null);
            response.setMessage("Users.");
            response.setSuccess(true);
            response.setStatus("200");
        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
            response.setStatus("403");
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Response<Credentials> getPremium(String username){
        Response<Credentials> response = new Response<>();
        JWTManager jwtManager = new JWTManager();
        Credentials credentials = new Credentials();
        try {
            User user = userRepository.findByUserName(username);
            if(user != null){
                if(!user.getUserRole().getName().equals("ROLE_PREMIUM")){
                    Calendar nextYearToday = Calendar.getInstance();
                    nextYearToday.add(Calendar.YEAR, 1);
                    user.setPremiumDate(nextYearToday.getTime());
                    user.setUserRole(userRoleRepository.findOne(4L));
                    for(int i = 0 ; i < 5; i++){
                        Cupon cupon = new Cupon();
                        cupon.setState(true);
                        cupon.setDiscount(100);
                        cupon.setUserCupon(user);
                        cupoRepository.save(cupon);
                    }
                    userRepository.save(user);
                }
                credentials.setId(user.getId());
                credentials.setUsername(user.getUsername());
                credentials.setRole(user.getUserRole().getName());
                credentials.setToken(jwtManager.createJWT(username,user.getPassword(),user.getUserRole().getName(),user.getId(),1200000,user.getImage(), user.getPremiumDate(), user.getEmail()));
                credentials.setPassword(user.getPassword());
                credentials.setEmail(user.getEmail());
                credentials.setPremiumDate(user.getPremiumDate());
                response.setData(credentials);
                response.setErrorMessage(null);
                response.setMessage("User premium " + username);
                response.setSuccess(true);
            }else {
                response.setData(null);
                response.setErrorMessage("UNAUTHORIZED");
                response.setMessage(null);
                response.setStatus("401");
            }

        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
        }

        return response;
    }

    @Override
    public Response<User> unUpgradePremium(String username){
        Response<User> response = new Response<>();
        try {
            User user = userRepository.findByUserName(username);
            if(user != null){
                user.setUserRole(userRoleRepository.findOne(3L));
                user.setPremiumDate(null);
                response.setData(userRepository.save(user));
                response.setErrorMessage(null);
                response.setMessage("User commun " + username);
                response.setSuccess(true);
            }else {
                response.setData(null);
                response.setErrorMessage("UNAUTHORIZED");
                response.setMessage(null);
                response.setStatus("401");
            }

        }catch (Exception e){
            response.setErrorMessage("There are problems with the register" + e.getLocalizedMessage());
            response.setMessage(null);
            response.setSuccess(false);
        }

        return response;
    }

}
