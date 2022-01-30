package lld.zomato.cmd.commands;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.UnProcessedOrderPutterTask;

public class AddOrderCommand implements RestaurantCommand {

	private Order order;
	BlockingQueue<Order> unProcessedOrders;
	private ExecutorService executor;

	public AddOrderCommand(Order order) {
		this.order = order;
	}

	@Override
	public void execute() {
		this.order.processState(order);
		this.order.next();
		this.order.processState(order);
		this.order.next();
	}

}
