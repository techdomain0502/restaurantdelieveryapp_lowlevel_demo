package lld.zomato.cmd.tasks;

import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;

public class OrderDelieveryPlannerTask implements Runnable {

	BlockingQueue<Order> delieveryOrders;
	OrdersDatabase db;
	Object lock;

	public OrderDelieveryPlannerTask(BlockingQueue<Order> delieveryOrders ) {
		this.delieveryOrders = delieveryOrders;
		this.db = OrdersDatabase.getInstance();
	}

	@Override
	public void run() {
		try {
			Order order = null;
			synchronized (OrderDelieveryPlannerTask.class) {
				 order = delieveryOrders.take();
			}
				order.setStatus(OrderStatus.OUT_FOR_DELIEVERY);
				db.setCurrentStatus(order.getId(), order.getStatus());
				System.out.println("order# " + order.getId()+" "+order.getStatus().getDescription()
				);
				//Thread.sleep(1500);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
