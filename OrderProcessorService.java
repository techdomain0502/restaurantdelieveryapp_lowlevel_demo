package lld.zomato.cmd;

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

public class OrderProcessorService {
	RestaurantCommand command = null;

	// corresponds to rest api end point like /add?order={1,...}
	void addOrder(Order order) {
		command = new AddOrderCommand(order);
		command.execute();
	}

	/**
	 * these below are internal apis not exposed to customer Executes as per
	 * restaurant manager current order status
	 */
	void preparingOrder() {
		command = new PreparingOrderCommand();
		command.execute();
	}

	void preparedOrder() {
		command = new PreparedOrderCommand();
		command.execute();

	}

	void delieverOrder() {
		command = new DelieverOrderCommand();
		command.execute();
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
