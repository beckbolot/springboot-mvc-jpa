package com.beck.springbootmvcjpa.service;

import com.beck.springbootmvcjpa.entity.FinalOrder;
import com.beck.springbootmvcjpa.respository.FinalOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FinalOrderService {

    private final FinalOrderRepository finalOrderRepository;

    public List<FinalOrder> getListFinalOrder(){
        return finalOrderRepository.getListFinalOrder();

    }

    @Transactional
    public void removeOrder(long id){
        finalOrderRepository.removeOrder(id);

    }


}
