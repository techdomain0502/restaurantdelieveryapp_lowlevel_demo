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
    private Order order;
    
	public PreparingOrderCommand(Order order) {
		this.order = order;
	}

	@Override
	public void execute() {
		this.order.processState(order);
		this.order.next();
	}

}
