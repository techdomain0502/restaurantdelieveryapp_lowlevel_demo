package lld.zomato.cmd.tasks;

import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;

public class UnProcessedOrdersPickerTask implements Runnable {

	BlockingQueue<Order> unprocessedOrders, processedOrders;
	OrdersDatabase db;
	Object lock;

	public UnProcessedOrdersPickerTask(BlockingQueue<Order> unProcessedOrders, BlockingQueue<Order> processedOrders) {
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
			Order order = null;
			synchronized (UnProcessedOrdersPickerTask.class) {
				order = unprocessedOrders.take();
			}
			System.out.println("preparing your order# " + order.getId());
			order.setStatus(OrderStatus.PROCESSED);
			db.setCurrentStatus(order.getId(), order.getStatus());
			
			synchronized (UnProcessedOrdersPickerTask.class) {
				processedOrders.put(order);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
