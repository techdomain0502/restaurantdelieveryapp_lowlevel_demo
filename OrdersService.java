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
    
	 void addOrder(Order order) {
		db.addOrder(order);
	   // usingThreads(order);
		//userExecService(order);
	    execService.submit(new UnProcessedOrderPutterTask(lock1,order, unProcessedOrders,
				  db));
	 }
	 
	 void preparingOrder() {
		  execService.submit(new UnProcessedOrdersPickerTask(lock2,unProcessedOrders,
				   processedOrders, db));
	 }
	 
	 void preparedOrder() {
		  execService.submit(new
				   OrderDelieveryPickerTask(lock3,processedOrders, delieveryOrders, db));
		 
	 }
	 
    void delieverOrder() {
		  execService.submit(new OrderDelieveryPlannerTask(lock4,delieveryOrders, db));
		
    }
	 

	private void usingThreads(Order order) {
			new Thread(new UnProcessedOrderPutterTask(lock1,order, unProcessedOrders, db)).start();
			new Thread(new UnProcessedOrdersPickerTask(lock2,unProcessedOrders, processedOrders, db)).start();
			new Thread(new OrderDelieveryPickerTask(lock3,processedOrders, delieveryOrders, db)).start();
			new Thread(new OrderDelieveryPlannerTask(lock4,delieveryOrders, db)).start();
		
		
	}

	Status getOrderCurrentStatus(int id) {
		return db.getCurrentStatus(id);
	}
}
