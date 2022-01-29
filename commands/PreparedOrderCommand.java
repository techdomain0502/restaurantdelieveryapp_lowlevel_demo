package lld.zomato.cmd.commands;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;

import lld.zomato.cmd.Order;
import lld.zomato.cmd.OrderDelieveryPickerTask;
import lld.zomato.cmd.UnProcessedOrdersPickerTask;

public class PreparedOrderCommand implements RestaurantCommand {
	private BlockingQueue<Order> processedOrders;
	private BlockingQueue<Order> delieveredOrders;
	private ExecutorService executor;

	public PreparedOrderCommand() {
		this.delieveredOrders = GlobalProvider.getGlobalProvider().getDelieveryOrders();
		this.processedOrders = GlobalProvider.getGlobalProvider().getProcessedOrders();
		executor = GlobalProvider.getGlobalProvider().getExecService();
	}

	@Override
	public void execute() {
		if (processedOrders.isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		executor.submit(new OrderDelieveryPickerTask(processedOrders, delieveredOrders));
	}

}
