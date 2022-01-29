package lld.zomato.cmd.tasks;

import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;

public class OrderDelieveryPickerTask implements Runnable {

	BlockingQueue<Order> processedOrders, delieveryOrders;
	OrdersDatabase db;
	Object lock;

	public OrderDelieveryPickerTask(BlockingQueue<Order> processedOrders, BlockingQueue<Order> delieveryOrders) {
		this.processedOrders = processedOrders;
		this.delieveryOrders = delieveryOrders;
		this.db = OrdersDatabase.getInstance();
	}

	@Override
	public void run() {
		try {
			Order order = null;
			synchronized (OrderDelieveryPickerTask.class) {
				 order = processedOrders.take();
			}
			Thread.sleep(1500);
			System.out.println("order# " + order.getId() + " " + order.getStatus() + " " + "ready for delievery:)");
			order.setStatus(OrderStatus.DELIEVERING);
			db.setCurrentStatus(order.getId(), order.getStatus());
			synchronized (OrderDelieveryPickerTask.class) {
				delieveryOrders.put(order);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
