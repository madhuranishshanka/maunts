package com.devspace.security.service.impl;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;
import com.devspace.security.repository.LoginAccountRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("userLoginDetailsService")
public class UserLoginDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "loginAccountRepository")
    private LoginAccountRepository loginAccountRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        try {
            LoginAccount account = loginAccountRepository.findByUserName(userName);
            Set<Role> roles = account.getRoles();

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (Role role : roles) {
                authorities.add(new GrantedAuthorityImpl(role.getName()));
            }

            UserDetails userDetails = new User(account.getUserName(), account.getPassword(), true, true, true, true, authorities);
            return userDetails;
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
