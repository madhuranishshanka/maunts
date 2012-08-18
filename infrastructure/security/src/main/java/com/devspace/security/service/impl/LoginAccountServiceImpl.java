package com.devspace.security.service.impl;

import com.devspace.logging.domain.Log;
import com.devspace.logging.domain.LogLevel;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;
import com.devspace.security.repository.LoginAccountRepository;
import com.devspace.security.repository.RoleRepository;
import com.devspace.security.service.LoginAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public LoginAccount createLoginAccount(String userName, String password, Set<Role> roles) throws EntityNotFoundException {
        LoginAccount loginAccount = new LoginAccount(userName, password);

        for (Role role : roles) {
            findRoleById(role.getId());
        }

        loginAccount.getRoles().addAll(roles);
        loginAccountRepository.save(loginAccount);
        return loginAccount;
    }

    @Log(logLevel = LogLevel.DEBUG)
    public LoginAccount findLoginAccountById(long id) throws EntityNotFoundException {
        return loginAccountRepository.findById(id);
    }

    @Log(logLevel = LogLevel.DEBUG)
    public LoginAccount findLoginAccountByUserName(String userName) throws EntityNotFoundException {
        return loginAccountRepository.findByUserName(userName);
    }

    @Log(logLevel = LogLevel.DEBUG)
    public void deleteLoginAccount(long id) throws EntityNotFoundException {
        loginAccountRepository.delete(id);
    }

    @Log(logLevel = LogLevel.DEBUG)
    public Role createRole(String roleName, String roleDescription) {
        Role role = new Role(roleName, roleDescription);
        roleRepository.save(role);
        return role;
    }

    @Log(logLevel = LogLevel.DEBUG)
    public Role findRoleById(long id) throws EntityNotFoundException {
        return roleRepository.findById(id);
    }

    @Log(logLevel = LogLevel.DEBUG)
    public Role findRoleByName(String roleName) throws EntityNotFoundException {
        return roleRepository.findByName(roleName);
    }

    @Log(logLevel = LogLevel.DEBUG)
    public void deleteRole(long id) throws EntityNotFoundException {
        // todo check whether the role is used in login accounts
        roleRepository.delete(id);
    }
}
