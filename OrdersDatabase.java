package lld.zomato.cmd;

import java.util.ArrayList;
import java.util.Optional;

import dsalgo.fastslowpointer.ReorderList;

public class OrdersDatabase {
	private static OrdersDatabase mInstance;

	ArrayList<Order> ordersList = new ArrayList();

	private OrdersDatabase() {
	}

	public static OrdersDatabase getInstance() {
		if (mInstance == null) {
			synchronized (OrdersDatabase.class) {
				if (mInstance == null) {
					mInstance = new OrdersDatabase();
				}
			}
		}
		return mInstance;
	}

	public void addOrder(Order order) {
		ordersList.add(order);
	}

	public Order removeOrder(int id) {
		Optional<Order> odr = ordersList.stream().filter(o -> id == o.id).findFirst();
		if (odr.isPresent()) {
			ordersList.remove(odr);
			return odr.get();
		} else {
			System.out.println("no such order with id " + id + " exists!!");
		}
		return null;
	}

	Status setCurrentStatus(int id, Status status) {
		Optional<Order> order = ordersList.stream().filter(or -> id == or.id).findFirst();
		if (order.isPresent()) {
			order.get().state = status;
			return status;
		} else
			return Status.NOT_AVAILABLE;
	}

	public Status getCurrentStatus(int id) {
		Optional<Order> order = ordersList.stream().filter(or -> id == or.id).findFirst();

		if (order.isPresent()) {
			return order.get().state;
		} else
			return Status.NOT_AVAILABLE;
	}
	
	public void getCurrentPendingOrders() {
		ordersList.stream().forEach(order->{
			System.out.println(order.id+" "+order.state);
		});
	}
	
}
