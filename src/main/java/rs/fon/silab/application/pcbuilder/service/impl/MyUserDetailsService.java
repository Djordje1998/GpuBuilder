package rs.fon.silab.application.pcbuilder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.fon.silab.application.pcbuilder.dao.Dao;
import rs.fon.silab.application.pcbuilder.model.impl.UserEntity;
import rs.fon.silab.application.pcbuilder.model.impl.UserProfileEntity;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private final Dao<UserEntity> repository;

    @Autowired
    public MyUserDetailsService(@Qualifier(value = "userDao") Dao<UserEntity> repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        try {
            user = repository.find(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UsernameNotFoundException("Service failure!" + ex.getMessage());
        }
        if (user == null) {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
        List<GrantedAuthority> ga = getGrantedAuthorities(user);
        return new User(username, user.getUserPassword(), true, true, true, true, ga);
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserEntity user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getProfile().getProfileName()));
        return authorities;
    }

}
