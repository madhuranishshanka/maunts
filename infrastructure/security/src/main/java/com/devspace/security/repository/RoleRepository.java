package com.devspace.security.repository;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.persistence.repository.CrudRepository;
import com.devspace.security.domain.Role;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface RoleRepository extends CrudRepository<Role>{

    Role findByName(String roleName) throws EntityNotFoundException;
}
