package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.FinalOrder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FinalOrderRepositoryImpl implements FinalOrderRepository{

    private final EntityManager entityManager;

    @Override
    public List<FinalOrder> getListFinalOrder() {
        return entityManager.createQuery("from FinalOrder", FinalOrder.class).getResultList();
    }

    @Override
    public void removeOrder(long id) {
        FinalOrder finalOrder = entityManager.find(FinalOrder.class, id);
        entityManager.remove(finalOrder);
        log.info("Final order was removed: " + finalOrder);

    }
}
