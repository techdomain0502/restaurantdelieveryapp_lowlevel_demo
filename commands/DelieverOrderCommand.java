package lld.zomato.cmd.commands;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;

import lld.zomato.cmd.Order;
import lld.zomato.cmd.OrderDelieveryPickerTask;
import lld.zomato.cmd.OrderDelieveryPlannerTask;
import lld.zomato.cmd.UnProcessedOrdersPickerTask;

public class DelieverOrderCommand implements RestaurantCommand {
	private BlockingQueue<Order> delieveredOrders;
	private ExecutorService executor;

	public DelieverOrderCommand() {
		this.delieveredOrders = GlobalProvider.getGlobalProvider().getDelieveryOrders();
		executor = GlobalProvider.getGlobalProvider().getExecService();
	}

	@Override
	public void execute() {
		if (delieveredOrders.isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		executor.submit(new OrderDelieveryPlannerTask(delieveredOrders));

	}

}
