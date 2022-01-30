package lld.zomato.cmd.tasks;

import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;

public class UnProcessedOrdersPickerTask implements Runnable {

	BlockingQueue<Order> unprocessedOrders, processedOrders;
	OrdersDatabase db;
	Object lock;
	Order order;

	public UnProcessedOrdersPickerTask(Order order, BlockingQueue<Order> unProcessedOrders,
			BlockingQueue<Order> processedOrders) {
		this.order = order;
		this.unprocessedOrders = unProcessedOrders;
		this.processedOrders = processedOrders;
		this.db = OrdersDatabase.getInstance();
	}

	@Override
	public void run() {
		try {
			/*
			 * Thread.sleep(3500);
			 */
			 
			System.out.println("preparing your order# " + order.getId());
			db.setCurrentStatus(order.getId(), order.getCurrentState());

			synchronized (UnProcessedOrdersPickerTask.class) {
				processedOrders.put(order);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
