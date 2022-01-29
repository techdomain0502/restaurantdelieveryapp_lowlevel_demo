package lld.zomato.cmd.providers;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import lld.zomato.cmd.model.Order;

public class GlobalProvider {

	private static GlobalProvider mInstance;

	private GlobalProvider() {
	}

	public static GlobalProvider getGlobalProvider() {
		if (mInstance == null) {
			synchronized (GlobalProvider.class) {
				if (mInstance == null) {
					mInstance = new GlobalProvider();
				}
			}
		}
		return mInstance;
	}

	private ExecutorService execService = Executors.newCachedThreadPool();
	private BlockingQueue<Order> unProcessedOrders = new LinkedBlockingDeque<>();
	private BlockingQueue<Order> processedOrders = new LinkedBlockingDeque<>();
	private BlockingQueue<Order> delieveryOrders = new LinkedBlockingDeque<>();
    private Scanner sc = new Scanner(System.in);
    
	public ExecutorService getExecService() {
		return execService;
	}

	public BlockingQueue<Order> getUnProcessedOrders() {
		return unProcessedOrders;
	}

	public BlockingQueue<Order> getProcessedOrders() {
		return processedOrders;
	}

	public BlockingQueue<Order> getDelieveryOrders() {
		return delieveryOrders;
	}

	public Scanner getScanner() {
		return sc;
	}
}
