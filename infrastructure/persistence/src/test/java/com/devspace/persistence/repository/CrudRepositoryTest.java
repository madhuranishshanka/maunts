package com.devspace.persistence.repository;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.persistence.mock.EntityBean;
import com.devspace.persistence.mock.MockCrudRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static junit.framework.Assert.fail;
import static org.junit.Assert.*;

//import org.hibernate.service.ServiceRegistry;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-persistence-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class CrudRepositoryTest {


    @Resource(name = "crudRepository")
    private MockCrudRepositoryImpl crudRepository;

        @Test
    public void testRoomCrud() {

        String name = "entity Name";
        String updatedName = "Updated Economy class";
        EntityBean persistedEntityBean = null;

        EntityBean entityBean = new EntityBean();
        entityBean.setName(name);

        crudRepository.save(entityBean);
        try {
            persistedEntityBean = crudRepository.findById(entityBean.getId());
            assertNotNull(persistedEntityBean);
            assertTrue("Un-matching EntityBean ", entityBean.equals(persistedEntityBean));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }

        entityBean.setName(updatedName);

        crudRepository.update(entityBean);

        try {
            persistedEntityBean = crudRepository.findById(entityBean.getId());
            assertNotNull(persistedEntityBean);
            assertTrue("Un-matching EntityBean ", entityBean.equals(persistedEntityBean));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }

        try {
            crudRepository.delete(entityBean.getId());
            persistedEntityBean = crudRepository.findById(entityBean.getId());
            fail("EntityBean object found");
        } catch (EntityNotFoundException e) {
        }
    }

}
