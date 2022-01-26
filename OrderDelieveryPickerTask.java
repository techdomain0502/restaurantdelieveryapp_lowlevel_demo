package lld.zomato.cmd;

import java.util.concurrent.BlockingQueue;

public class OrderDelieveryPickerTask implements Runnable {


	BlockingQueue<Order> processedOrders, delieveryOrders;
	OrdersDatabase db;
	Object lock;
	public OrderDelieveryPickerTask(BlockingQueue<Order> processedOrders, BlockingQueue<Order> delieveryOrders ) {
		this.processedOrders = processedOrders;
		this.delieveryOrders = delieveryOrders;
		this.db = OrdersDatabase.getInstance(); 
	}

	@Override
	public void run() {
		try {
			
			synchronized (OrderDelieveryPickerTask.class) {
				Order order = processedOrders.take();
 				Thread.sleep(1500);
 				System.out.println("order# " + order.id+" "+order.state+" "+"ready for delievery:)");
				order.state = Status.DELIEVERING;
				db.setCurrentStatus(order.id, order.state);
				delieveryOrders.put(order);	
			}
				

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
