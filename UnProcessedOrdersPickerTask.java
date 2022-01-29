package lld.zomato.cmd;

import java.util.concurrent.BlockingQueue;

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
			Thread.sleep(3500);
			
			Order order = null;
			synchronized (UnProcessedOrdersPickerTask.class) {
				order = unprocessedOrders.take();
			}
			System.out.println("preparing your order# " + order.id);
			order.state = Status.PROCESSED;
			db.setCurrentStatus(order.id, order.state);
			
			synchronized (UnProcessedOrdersPickerTask.class) {
				processedOrders.put(order);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
