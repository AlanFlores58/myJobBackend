package com.newSoftMex.filter;

import com.newSoftMex.security.JWTAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan.flores on 1/9/17.
 */

@Component
public class MyFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void destroy() {
        System.out.println("destroy");
    }


    //ya tengo un token dejame pasar
    private void processTokenAuthentication(String token) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }

    //
    private Authentication tryToAuthenticateWithToken(String token) {
        JWTAuthenticationToken requestAuthentication = new JWTAuthenticationToken(token);
        return tryToAuthenticate(requestAuthentication);
    }


    //
    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new AuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        return responseAuthentication;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("Authorization");

        HttpServletResponse response = servletResponse;
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,HEAD,OPTIONS");
        response.addHeader("Access-Control-Max-Age", "360");
        response.addHeader("Access-Control-Allow-Headers", "Accept,x-requested-with,Content-Type,username,Authorization, x-authorization-token");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.addHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        if (auth != null && (auth.startsWith("Bearer ") || auth.startsWith("bearer "))) {
            String token = auth.substring(7);
            try {

                processTokenAuthentication(token);

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(token);
        }

        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(200);
        }else {
            filterChain.doFilter(request, servletResponse);
        }

    }
}
