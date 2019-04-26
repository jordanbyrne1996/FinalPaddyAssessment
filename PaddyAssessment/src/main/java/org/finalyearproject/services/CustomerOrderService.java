package org.finalyearproject.services;

import java.util.ArrayList;

import org.finalyearproject.entities.CustomerOrder;
import org.finalyearproject.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	public CustomerOrder findById(int id) {
		return customerOrderRepository.findByOrderId(id);
	}

	public ArrayList<CustomerOrder> findOrdersByUserEmail(String email) {
		return customerOrderRepository.findOrdersByUserEmail(email);
	}

	public void saveOrder(CustomerOrder order) {
		customerOrderRepository.save(order);
	}

}
