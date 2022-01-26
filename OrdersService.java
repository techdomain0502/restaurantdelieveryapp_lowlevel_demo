package lld.zomato.cmd;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.*;

public class OrdersService {
	ExecutorService execService = Executors.newCachedThreadPool();
	BlockingQueue<Order> unProcessedOrders = new LinkedBlockingDeque<>();
	BlockingQueue<Order> processedOrders = new LinkedBlockingDeque<>();
	BlockingQueue<Order> delieveryOrders = new LinkedBlockingDeque<>();
	OrdersDatabase db = new OrdersDatabase();
 
	// corresponds to rest api end point like /add?order={1,...}
	void addOrder(Order order) {
		db.addOrder(order);
		execService.submit(new UnProcessedOrderPutterTask(  order, unProcessedOrders, db));
	}

	/**
	 * these below are internal apis not exposed to customer Executes as per
	 * restaurant manager current order status
	 */
	void preparingOrder() {
		if (unProcessedOrders.isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		execService.submit(new UnProcessedOrdersPickerTask(  unProcessedOrders, processedOrders, db));
	}

	void preparedOrder() {
		if (processedOrders.isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		execService.submit(new OrderDelieveryPickerTask( processedOrders, delieveryOrders, db));

	}

	void delieverOrder() {
		if (delieveryOrders.isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		execService.submit(new OrderDelieveryPlannerTask( delieveryOrders, db));
	}

	/*
	 * get the current status of the order
	 */
	void getOrderCurrentStatus() {
		System.out.println("Enter order#");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		Status s = db.getCurrentStatus(id);
		System.out.println("Status of order# " + id + " " + s);
	}
	
	void shutDownRestaurant() {
		System.out.println("shutting down restaurant for the day!!");
		execService.shutdown();
	}
}
