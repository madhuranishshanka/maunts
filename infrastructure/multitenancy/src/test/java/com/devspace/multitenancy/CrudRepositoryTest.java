package com.devspace.multitenancy;

import com.devspace.multitenancy.mock.EntityBean;
import com.devspace.multitenancy.mock.MockCrudRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import org.hibernate.service.ServiceRegistry;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:multitenancy-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class CrudRepositoryTest {


    @Resource(name = "crudRepository")
    private MockCrudRepositoryImpl crudRepository;


    @Test
    public void testMultitenancy() {

        EntityBean entityBean1 = new EntityBean();
        entityBean1.setTenantId("TID1");
        entityBean1.setName("laptop 1");

        EntityBean entityBean2 = new EntityBean();
        entityBean2.setTenantId("TID2");
        entityBean2.setName("laptop 2");

        crudRepository.save(entityBean1);
        crudRepository.save(entityBean2);

        List<EntityBean> all = crudRepository.findAll();


        assertEquals(1, all.size());

    }
}
