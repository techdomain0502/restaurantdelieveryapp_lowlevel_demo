package lld.zomato.cmd.commands;

import lld.zomato.cmd.model.OrdersDatabase;

public class GetPendingOrdersCommands implements RestaurantCommand{

	@Override
	public void execute() {
		System.out.println("Here is the list of orders so far");
		OrdersDatabase.getInstance().getCurrentPendingOrders();
	}

}
