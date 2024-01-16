package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.FinalOrder;

import java.util.List;

public interface FinalOrderRepository {

    List<FinalOrder> getListFinalOrder();

    void removeOrder(long id);


}
