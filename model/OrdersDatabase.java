package lld.zomato.cmd.model;

import java.util.ArrayList;
import java.util.Optional;

import dsalgo.fastslowpointer.ReorderList;
import lld.zomato.cmd.consts.OrderStatus;

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
		Optional<Order> odr = ordersList.stream().filter(o -> id == o.getId()).findFirst();
		if (odr.isPresent()) {
			ordersList.remove(odr);
			return odr.get();
		} else {
			System.out.println("no such order with id " + id + " exists!!");
		}
		return null;
	}

	public OrderStatus setCurrentStatus(int id, OrderStatus status) {
		Optional<Order> order = ordersList.stream().filter(or -> id == or.getId()).findFirst();
		if (order.isPresent()) {
			order.get().setStatus(status);
			return status;
		} else
			return OrderStatus.NOT_AVAILABLE;
	}

	public OrderStatus getCurrentStatus(int id) {
		Optional<Order> order = ordersList.stream().filter(or -> id == or.getId()).findFirst();

		if (order.isPresent()) {
			return order.get().getStatus();
		} else
			return OrderStatus.NOT_AVAILABLE;
	}

	public void getCurrentPendingOrders() {
		ordersList.stream().forEach(order -> {
			System.out.println(order.getId() + " " + order.getStatus());
		});
	}

}
