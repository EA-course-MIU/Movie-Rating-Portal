package com.edu.miu.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class AwesomeUserDetails implements UserDetails {

    private String id;
    private String email;

    @JsonIgnore
    private String password;

    private List<String> roles;

    public AwesomeUserDetails(Claims claims) {
        this.id = claims.getSubject();
        this.email = claims.get("email", String.class);
        Map<String, Object> realmAccess = (Map<String, Object>) claims.get("realm_access");
        this.roles = ((List<String>)realmAccess.get("roles"));
        System.out.println(claims);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(Object::toString)
                .map(r -> "ROLE_" + r.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public boolean isAdmin() {
        return roles.contains("ADMIN");
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
