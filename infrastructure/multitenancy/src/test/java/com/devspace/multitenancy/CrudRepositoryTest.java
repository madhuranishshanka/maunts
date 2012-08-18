package com.devspace.multitenancy;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.multitenancy.dummy.DummyCrudRepositoryImpl;
import com.devspace.multitenancy.dummy.DummyEntityBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

//import org.hibernate.service.ServiceRegistry;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-multitenancy-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class CrudRepositoryTest {


    @Resource(name = "dummyCrudRepository")
    private DummyCrudRepositoryImpl crudRepository;


    @Test
    public void testMultitenancy() {

        TenantContext.setTenant("TID1");
        DummyEntityBean entityBean1 = new DummyEntityBean();
        entityBean1.setName("laptop 1");
        crudRepository.save(entityBean1);

        TenantContext.setTenant("TID2");
        DummyEntityBean entityBean2 = new DummyEntityBean();
        entityBean2.setName("laptop 2");

        crudRepository.save(entityBean2);

        TenantContext.setTenant("TID2");
        List<DummyEntityBean> all = crudRepository.findAll();


        assertEquals(1, all.size());

    }
}
