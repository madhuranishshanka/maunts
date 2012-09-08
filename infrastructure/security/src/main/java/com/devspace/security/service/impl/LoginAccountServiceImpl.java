package com.devspace.security.service.impl;

import com.devspace.logging.domain.Log;
import com.devspace.logging.domain.LogLevel;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;
import com.devspace.security.exception.LoginAccountNotFound;
import com.devspace.security.exception.RoleNotFoundException;
import com.devspace.security.repository.LoginAccountRepository;
import com.devspace.security.repository.RoleRepository;
import com.devspace.security.service.LoginAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("loginAccountService")
public class LoginAccountServiceImpl implements LoginAccountService {

    @Resource(name = "roleRepository")
    private RoleRepository roleRepository;

    @Resource(name = "loginAccountRepository")
    private LoginAccountRepository loginAccountRepository;

    @Log(logLevel = LogLevel.DEBUG)
    public LoginAccount createLoginAccount(String userName, String password,
                                           Set<String> roleNames) throws RoleNotFoundException {
        LoginAccount loginAccount = new LoginAccount(userName, password);

        List<Role> roles = new ArrayList<Role>();
        for (String roleName : roleNames) {
            roles.add(findRoleByName(roleName));
        }

        loginAccount.getRoles().addAll(roles);
        loginAccountRepository.save(loginAccount);
        return loginAccount;
    }

    @Log(logLevel = LogLevel.DEBUG)
    public LoginAccount findLoginAccountById(long id) throws LoginAccountNotFound {
        try {
            return loginAccountRepository.findById(id);
        } catch (EntityNotFoundException e) {
            throw new LoginAccountNotFound(e.getMessage());
        }
    }

    @Log(logLevel = LogLevel.DEBUG)
    public LoginAccount findLoginAccountByUserName(String userName) throws LoginAccountNotFound {
        try {
            return loginAccountRepository.findByUserName(userName);
        } catch (EntityNotFoundException e) {
            throw new LoginAccountNotFound(e.getMessage());
        }
    }

    @Log(logLevel = LogLevel.DEBUG)
    public void deleteLoginAccount(long id) throws LoginAccountNotFound {
        try {
            loginAccountRepository.delete(id);
        } catch (EntityNotFoundException e) {
            throw new LoginAccountNotFound(e.getMessage());
        }
    }

    @Log(logLevel = LogLevel.DEBUG)
    public Role createRole(String roleName, String roleDescription) {
        Role role = new Role(roleName, roleDescription);
        roleRepository.save(role);
        return role;
    }

    @Log(logLevel = LogLevel.DEBUG)
    public Role findRoleById(long id) throws RoleNotFoundException {
        try {
            return roleRepository.findById(id);
        } catch (EntityNotFoundException e) {
            throw new RoleNotFoundException(e.getMessage());
        }
    }

    @Log(logLevel = LogLevel.DEBUG)
    public Role findRoleByName(String roleName) throws RoleNotFoundException {
        try {
            return roleRepository.findByName(roleName);
        } catch (EntityNotFoundException e) {
            throw new RoleNotFoundException(e.getMessage());
        }
    }

    @Log(logLevel = LogLevel.DEBUG)
    public void deleteRole(long id) throws RoleNotFoundException {
        // todo check whether the role is used in login accounts
        try {
            roleRepository.delete(id);
        } catch (EntityNotFoundException e) {
            throw new RoleNotFoundException(e.getMessage());
        }
    }
}
