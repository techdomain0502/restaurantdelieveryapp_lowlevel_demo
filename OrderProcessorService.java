package lld.zomato.cmd;

import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.commands.AddOrderCommand;
import lld.zomato.cmd.commands.CancelOrderCommand;
import lld.zomato.cmd.commands.DelieverOrderCommand;
import lld.zomato.cmd.commands.GetOrderStatusCommand;
import lld.zomato.cmd.commands.GetPendingOrdersCommands;
import lld.zomato.cmd.commands.PreparedOrderCommand;
import lld.zomato.cmd.commands.PreparingOrderCommand;
import lld.zomato.cmd.commands.RestaurantCommand;
import lld.zomato.cmd.commands.ShowMenuCommand;
import lld.zomato.cmd.commands.ShutDownRestaurantCommand;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.states.InitState;
import lld.zomato.cmd.states.OrderState;

public class OrderProcessorService {
	RestaurantCommand command = null;

	// corresponds to rest api end point like /add?order={1,...}
	void addOrder() {
		int orderId = GlobalProvider.getGlobalProvider().getOrderId();
		Order order = new Order(orderId);
		order.setState(new InitState());
		command = new AddOrderCommand(order);
		command.execute();
	}

	/**
	 * these below are internal apis not exposed to customer Executes as per
	 * restaurant manager current order status
	 */
	void preparingOrder() {
		BlockingQueue<Order> orders = GlobalProvider.getGlobalProvider().getUnProcessedOrders();

		if (orders.size() == 0) {
			System.out.println("sorry no pending orders, for preparing");
			return;
		}
		Order order;
		try {
			order = orders.take();
			command = new PreparingOrderCommand(order);
			command.execute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	void preparedOrder() {
		BlockingQueue<Order> orders = GlobalProvider.getGlobalProvider().getProcessedOrders();

		if (orders.size() == 0) {
			System.out.println("no pending orders for marking as prepared \n");
			return;
		}

		Order order;
		try {
			order = orders.take();
			command = new PreparedOrderCommand(order);
			command.execute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	void delieverOrder() {
		BlockingQueue<Order> orders = GlobalProvider.getGlobalProvider().getDelieveryOrders();

		if (orders.size() == 0) {
			System.out.println("no pending orders for marking for delievery \n");
			return;
		}

		Order order;
		try {
			order = orders.take();
			command = new DelieverOrderCommand(order);
			command.execute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void getOrderCurrentStatus() {
		command = new GetOrderStatusCommand();
		command.execute();
	}

	void shutDownRestaurant() {
		command = new ShutDownRestaurantCommand();
		command.execute();
	}

	public void cancelOrder() {
		command = new CancelOrderCommand();
		command.execute();
	}

	public void getPendingOrderList() {
		command = new GetPendingOrdersCommands();
		command.execute();
	}

	public void showMenu() {
		command = new ShowMenuCommand();
		command.execute();

	}
}
