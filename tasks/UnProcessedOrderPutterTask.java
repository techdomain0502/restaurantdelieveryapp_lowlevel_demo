package lld.zomato.cmd.tasks;

import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;

public class UnProcessedOrderPutterTask implements Runnable {

	BlockingQueue<Order> unprocessedOrders;
	Order order;
	OrdersDatabase db;
	Object lock;

	public UnProcessedOrderPutterTask(Order order, BlockingQueue<Order> unprocessedOrders) {
		super();
		this.order = order;
		this.unprocessedOrders = unprocessedOrders;
		this.db = OrdersDatabase.getInstance();
	}

	@Override
	public void run() {
		try {
			order.setStatus( OrderStatus.RECEIVED);
			db.setCurrentStatus(order.getId(), order.getStatus());
			Thread.sleep(2500);
			System.out.println("order#" + order.getId() + " " + order.getStatus());

			synchronized (UnProcessedOrderPutterTask.class) {
				unprocessedOrders.put(order);
			}

		} catch (InterruptedException e) {
			System.out.println("exceptin received");
			e.printStackTrace();
		}
	}

}
