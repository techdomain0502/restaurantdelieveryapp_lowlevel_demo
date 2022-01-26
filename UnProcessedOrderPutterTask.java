package lld.zomato.cmd;

import java.util.concurrent.BlockingQueue;

public class UnProcessedOrderPutterTask implements Runnable {

	BlockingQueue<Order> unprocessedOrders;
	Order order;
	OrdersDatabase db;
    Object lock;
	public UnProcessedOrderPutterTask(Order order, BlockingQueue<Order> unprocessedOrders ) {
		super();
		this.order = order;
		this.unprocessedOrders = unprocessedOrders;
		this.db = OrdersDatabase.getInstance();
	}

	@Override
	public void run() {
		try {
			synchronized (UnProcessedOrderPutterTask.class) {
				order.state = Status.RECEIVED;
				db.setCurrentStatus(order.id, order.state);
				System.out.println("order#"+order.id+" "+order.state);
				Thread.sleep(500);
				unprocessedOrders.put(order);	
			}
			
			
		} catch (InterruptedException e) {
			System.out.println("exceptin received");
			e.printStackTrace();
		}
	}

}
