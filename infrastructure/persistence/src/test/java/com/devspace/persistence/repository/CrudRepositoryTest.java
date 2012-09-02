package com.devspace.persistence.repository;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.persistence.dummy.DummyCrudRepositoryImpl;
import com.devspace.persistence.dummy.DummyEntityBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
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


    @Resource(name = "dummyCrudRepository")
    private DummyCrudRepositoryImpl crudRepository;

    @Test
    public void testRoomCrud() {

        String name = "entity Name";
        String updatedName = "Updated Economy class";
        DummyEntityBean persistedEntityBean = null;

        DummyEntityBean entityBean = new DummyEntityBean();
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

    @Test
    public void testFindAll() {
        TenantContext.setTenant("tenantId");
        DummyEntityBean entity1 = new DummyEntityBean();
        entity1.setName("entity1");
        crudRepository.save(entity1);

        DummyEntityBean entity2 = new DummyEntityBean();
        entity1.setName("entity2");
        crudRepository.save(entity2);

        DummyEntityBean entity3 = new DummyEntityBean();
        entity1.setName("entity3");
        crudRepository.save(entity3);

        DummyEntityBean entity4 = new DummyEntityBean();
        entity1.setName("entity4");
        crudRepository.save(entity4);

        List<DummyEntityBean> all = crudRepository.findAll();

        assertNotNull(all);
        assertTrue(all.size() == 4);

    }
}
