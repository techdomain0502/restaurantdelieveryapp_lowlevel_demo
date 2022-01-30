package lld.zomato.cmd.model;

import java.util.ArrayList;
import java.util.Optional;

import dsalgo.fastslowpointer.ReorderList;
import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.states.ErrorState;
import lld.zomato.cmd.states.InitState;
import lld.zomato.cmd.states.OrderState;

public class OrdersDatabase {
	private static OrdersDatabase mInstance;

	ArrayList<OrderItem> ordersList = new ArrayList();

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
		int id = order.getId();
		OrderState state = order.getCurrentState();
		OrderItem orderItem = new OrderItem();
		orderItem.setId(id);
		orderItem.setState(orderItem.getState());
		ordersList.add(orderItem);
	}

	public void removeOrder(int id) {
		Optional<OrderItem> odr = ordersList.stream().filter(o -> id == o.getId()).findFirst();
		if (odr.isPresent()) {
			ordersList.remove(odr.get());
		} else {
			System.out.println("no such order with id " + id + " exists!!");
		}
		 
	}

	public OrderState setCurrentStatus(int id, OrderState state) {
		Optional<OrderItem> order = ordersList.stream().filter(or -> id == or.getId()).findFirst();
		if (order.isPresent()) {
			order.get().setState(state.toString());
			return state;
		} else
			return new ErrorState();
	}

	public String getCurrentStatus(int id) {
		Optional<OrderItem> order = ordersList.stream().filter(or -> id == or.getId()).findFirst();

		if (order.isPresent()) {
			return order.get().getState();
		} else
			return new ErrorState().toString();
	}

	public void getCurrentPendingOrders() {
		ordersList.stream().forEach(order -> {
			System.out.println(order.getId() + " " + order.getState());
		});
	}

	
	public static void main(String[] args) {
		OrderItem item = new OrderItem();
		item.setId(1);
		item.setState(new InitState().toString());
		System.out.println(item);
		 
		
	}
	
}
