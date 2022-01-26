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
	Object lock1 = new Object();
	Object lock2 = new Object();
	Object lock3 = new Object();
	Object lock4 = new Object();

	//corresponds to rest api end point like /add?order={1,...}
	void addOrder(Order order) {
		db.addOrder(order);
		// usingThreads(order);
		// userExecService(order);
		execService.submit(new UnProcessedOrderPutterTask(lock1, order, unProcessedOrders, db));
	}

	/** these below are internal apis not exposed to customer
	 *  Executes as per restaurant manager current order status
	 */
	void preparingOrder() {
		execService.submit(new UnProcessedOrdersPickerTask(lock2, unProcessedOrders, processedOrders, db));
	}

	void preparedOrder() {
		execService.submit(new OrderDelieveryPickerTask(lock3, processedOrders, delieveryOrders, db));

	}

	void delieverOrder() {
		execService.submit(new OrderDelieveryPlannerTask(lock4, delieveryOrders, db));

	}


	void getOrderCurrentStatus() {
		System.out.println("Enter order#");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		Status s = db.getCurrentStatus(id);
		System.out.println("Status of order# "+id+ " "+s);
	}
}
