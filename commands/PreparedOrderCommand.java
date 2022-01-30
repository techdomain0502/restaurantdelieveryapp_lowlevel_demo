package lld.zomato.cmd.commands;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.OrderDelieveryPickerTask;
import lld.zomato.cmd.tasks.UnProcessedOrdersPickerTask;

public class PreparedOrderCommand implements RestaurantCommand {
	private BlockingQueue<Order> processedOrders;
	private BlockingQueue<Order> delieveredOrders;
	private ExecutorService executor;
    private Order order;
	public PreparedOrderCommand(Order order) {
		this.order = order;
	}

	@Override
	public void execute() {
		this.order.processState(this.order);
		this.order.next();
		
	}

}
