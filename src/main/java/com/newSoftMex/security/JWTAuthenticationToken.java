package com.newSoftMex.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * Created by alan.flores on 1/16/17.
 */
public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    private String username;


    public JWTAuthenticationToken(String token) {
        super((Collection)null);
        this.token = token;
    }

    public JWTAuthenticationToken(String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
    }


    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    public void setPrincipal(Object username) {
        this.username = (String) username;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
