package lld.zomato.cmd.tasks;

import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;

public class OrderDelieveryPickerTask implements Runnable {

	BlockingQueue<Order> processedOrders, delieveryOrders;
	OrdersDatabase db;
	Object lock;
	Order order;

	public OrderDelieveryPickerTask(Order order, BlockingQueue<Order> processedOrders, BlockingQueue<Order> delieveryOrders) {
		this.processedOrders = processedOrders;
		this.delieveryOrders = delieveryOrders;
		this.db = OrdersDatabase.getInstance();
		this.order = order;
	}

	@Override
	public void run() {
		try {
			//Threaead.sleep(1500);
			db.setCurrentStatus(order.getId(), order.getCurrentState());
			synchronized (OrderDelieveryPickerTask.class) {
				delieveryOrders.put(order);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
