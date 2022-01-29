package lld.zomato.cmd.commands;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import lld.zomato.cmd.Order;
import lld.zomato.cmd.OrdersDatabase;
import lld.zomato.cmd.UnProcessedOrderPutterTask;

public class AddOrderCommand implements RestaurantCommand {

	private Order order;
	BlockingQueue<Order> unProcessedOrders;
	private ExecutorService executor;

	public AddOrderCommand(Order order) {
		this.order = order;
		this.unProcessedOrders = GlobalProvider.getGlobalProvider().getUnProcessedOrders();
		this.executor = GlobalProvider.getGlobalProvider().getExecService();
	}

	@Override
	public void execute() {
		OrdersDatabase.getInstance().addOrder(this.order);
		executor.submit(new UnProcessedOrderPutterTask(order, unProcessedOrders));
	}

}
