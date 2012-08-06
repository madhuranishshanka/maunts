package com.devspace.security.repository.impl;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;
import com.devspace.security.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("roleRepository")
public class RoleRepositoryImpl extends BaseRepositoryImpl<Role> implements RoleRepository {
    @Override
    public Class<Role> getClassType() {
        return Role.class;
    }

    public Role findByName(String name) throws EntityNotFoundException {
        Query query = getEntityManager().createQuery("select o from Role o where o.name =:roleName");
        query.setParameter("roleName", name);
        Role role = (Role) query.getSingleResult();

        if (role == null) {
            throw new EntityNotFoundException(LoginAccount.class, "No Role found for the given role name" + name);
        }
        return role;
    }
}
