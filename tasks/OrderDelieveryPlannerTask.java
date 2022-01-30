package lld.zomato.cmd.tasks;

import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;

public class OrderDelieveryPlannerTask implements Runnable {

	BlockingQueue<Order> delieveryOrders;
	OrdersDatabase db;
	Object lock;
	Order order;

	public OrderDelieveryPlannerTask(Order order, BlockingQueue<Order> delieveryOrders) {
		this.delieveryOrders = delieveryOrders;
		this.db = OrdersDatabase.getInstance();
		this.order = order;
	}

	@Override
	public void run() {
		db.setCurrentStatus(order.getId(), order.getCurrentState());

	}
}
