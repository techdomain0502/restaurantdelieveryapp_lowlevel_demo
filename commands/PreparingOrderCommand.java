package lld.zomato.cmd.commands;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.UnProcessedOrdersPickerTask;

public class PreparingOrderCommand implements RestaurantCommand {
	private BlockingQueue<Order> unProcessedOrders;
	private BlockingQueue<Order> processedOrders;
	private ExecutorService executor;

	public PreparingOrderCommand() {
		this.unProcessedOrders = GlobalProvider.getGlobalProvider().getUnProcessedOrders();
		this.processedOrders = GlobalProvider.getGlobalProvider().getProcessedOrders();
		executor = GlobalProvider.getGlobalProvider().getExecService();
	}

	@Override
	public void execute() {
		if (unProcessedOrders.isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		executor.submit(new UnProcessedOrdersPickerTask(  unProcessedOrders, processedOrders));
	}

}
