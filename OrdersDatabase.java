package lld.zomato.cmd;

import java.util.ArrayList;
import java.util.Optional;

import dsalgo.fastslowpointer.ReorderList;

public class OrdersDatabase {
	ArrayList<Order> ordersList = new ArrayList();

	void addOrder(Order order) {
		ordersList.add(order);
	}

	Status setCurrentStatus(int id, Status status) {
		Optional<Order> order = ordersList.stream().filter(or -> id == or.id).findFirst();
		if (order.isPresent()) {
			order.get().state = status;
			return status;
		} else
			return Status.NOT_AVAILABLE;
	}

	Status getCurrentStatus(int id) {
		Optional<Order> order = ordersList.stream().filter(or -> id == or.id).findFirst();

		if (order.isPresent()) {
			return order.get().state;
		} else
			return Status.NOT_AVAILABLE;
	}
}
