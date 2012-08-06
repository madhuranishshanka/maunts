package com.devspace.security.service;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;

import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface LoginAccountService {

    LoginAccount createLoginAccount(String userName, String password, Set<Role> roles) throws EntityNotFoundException;

    LoginAccount findLoginAccountById(long id) throws EntityNotFoundException;

    LoginAccount findLoginAccountByUserName(String userName) throws EntityNotFoundException;

    void deleteLoginAccount(long id) throws EntityNotFoundException;

    Role createRole(String roleName, String roleDescription);

    Role findRoleById(long id) throws EntityNotFoundException;

    Role findRoleByName(String roleName) throws EntityNotFoundException;

    void deleteRole(long id) throws EntityNotFoundException;
}
