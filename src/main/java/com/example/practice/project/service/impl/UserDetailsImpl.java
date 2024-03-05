package com.example.practice.project.service.impl;


import com.example.practice.project.dto.UserDto;
import com.example.practice.project.entity.User;
import com.example.practice.project.entity.UserRole;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    private boolean firstTimeLogin;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * constructors.
     *
     * @param id                  id
     * @param username            username
     * @param email               email
     * @param authorities         authorities
     */
    public UserDetailsImpl(String password, Long id, String username, String email, Collection<? extends GrantedAuthority> authorities) {
        this.password = password;
        this.id = id;
        this.username = username;
        this.email = email;
        this.authorities = authorities;
    }

    /**
     * build.
     *
     * @param user      user
     * @return UserDetailsImpl
     */
    public static UserDetailsImpl build(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : user.getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new UserDetailsImpl(user.getPassword(), user.getId(), user.getUsername(), user.getEmail(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}