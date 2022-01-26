package lld.zomato.cmd;

import java.util.concurrent.BlockingQueue;

public class OrderDelieveryPlannerTask implements Runnable {

	BlockingQueue<Order> delieveryOrders;
	OrdersDatabase db;
	Object lock;

	public OrderDelieveryPlannerTask(Object lock,BlockingQueue<Order> delieveryOrders, OrdersDatabase db) {
		this.delieveryOrders = delieveryOrders;
		this.db = db;
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (db) {
				Order order = delieveryOrders.take();
				order.state = Status.OUT_FOR_DELIEVERY;
				db.setCurrentStatus(order.id, order.state);
				System.out.println("order# " + order.id+" "+order.state);
				Thread.sleep(1500);
				order.state = Status.DELIEVERD;
				db.setCurrentStatus(order.id, order.state);
				System.out.println("order# " + order.id+" "+order.state);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
