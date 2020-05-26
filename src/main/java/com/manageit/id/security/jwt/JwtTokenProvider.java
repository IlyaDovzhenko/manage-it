package com.manageit.id.security.jwt;

import com.manageit.id.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtTokenProvider {

    public String createToken(String username, List<Role> roles) {

    }

    public Authentication getAuthentication(String token) {

    }

    public String getUsername(String token) {

    }

    public boolean validateToken(String token) {

    }

    private List<Role> getRoleNames(List<Role> roles) {

    }

}
