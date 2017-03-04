package com.newSoftMex.security;

import com.newSoftMex.model.Credentials;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan.flores on 1/16/17.
 */
public class JWTAuthenticationProvider implements AuthenticationProvider{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken)authentication;
        JWTAuthenticationToken newJwtAuthenticationToken= null;

        String token = (String)jwtAuthenticationToken.getCredentials();

        try {
            Credentials credentials = (new JWTManager()).parseJWT(token);
            newJwtAuthenticationToken = new JWTAuthenticationToken(token, getRolesFromPerson(credentials));
            newJwtAuthenticationToken.setPrincipal(credentials.getUsername());
            newJwtAuthenticationToken.setAuthenticated(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newJwtAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthenticationToken.class);
    }

    private List<GrantedAuthority> getRolesFromPerson(Credentials credentials) {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(credentials.getRole()));
        return roles;
    }
}
