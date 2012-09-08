package com.devspace.security.service;

import com.devspace.security.exception.LoginAccountNotFound;
import com.devspace.security.exception.RoleNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;

import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface LoginAccountService {

    LoginAccount createLoginAccount(String userName, String password, Set<String> roleNames)
            throws RoleNotFoundException;

    LoginAccount findLoginAccountById(long id) throws LoginAccountNotFound;

    LoginAccount findLoginAccountByUserName(String userName) throws LoginAccountNotFound;

    void deleteLoginAccount(long id) throws LoginAccountNotFound;

    Role createRole(String roleName, String roleDescription);

    Role findRoleById(long id) throws RoleNotFoundException;

    Role findRoleByName(String roleName) throws RoleNotFoundException;

    void deleteRole(long id) throws RoleNotFoundException;
}
