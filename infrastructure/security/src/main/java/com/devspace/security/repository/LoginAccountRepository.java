package com.devspace.security.repository;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.persistence.repository.CrudRepository;
import com.devspace.security.domain.LoginAccount;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface LoginAccountRepository extends CrudRepository<LoginAccount>{

     LoginAccount findByUserName(String userName) throws EntityNotFoundException;
}
