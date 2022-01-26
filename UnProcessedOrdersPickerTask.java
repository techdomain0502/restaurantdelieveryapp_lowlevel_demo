package lld.zomato.cmd;

import java.util.concurrent.BlockingQueue;

public class UnProcessedOrdersPickerTask implements Runnable {

	BlockingQueue<Order> unprocessedOrders, processedOrders;
	OrdersDatabase db;
	Object lock;
	public UnProcessedOrdersPickerTask(Object lock,BlockingQueue<Order> unProcessedOrders, BlockingQueue<Order> processedOrders,
			OrdersDatabase db) {
		this.unprocessedOrders = unProcessedOrders;
		this.processedOrders = processedOrders;
		this.db = db;
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			Order order = unprocessedOrders.take();
			synchronized (lock) {
				System.out.println("processing your order# " + order.id);
				Thread.sleep(1500);
				order.state = Status.PROCESSED;
				db.setCurrentStatus(order.id, order.state);
				processedOrders.put(order);
			}
			

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
