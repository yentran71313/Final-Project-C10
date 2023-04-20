package com.cg.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;

    private String fullName;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(String accessToken, Long id, String username, String name,String fullName, Collection<? extends GrantedAuthority> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.name = name;
        this.fullName = fullName;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}