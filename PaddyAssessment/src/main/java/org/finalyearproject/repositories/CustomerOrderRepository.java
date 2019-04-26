package org.finalyearproject.repositories;

import java.util.ArrayList;

import org.finalyearproject.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

	CustomerOrder findByOrderId(int orderId);

	ArrayList<CustomerOrder> findOrdersByUserEmail(String email);
}
