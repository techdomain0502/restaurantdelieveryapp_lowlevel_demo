package lld.zomato.cmd.commands;

import lld.zomato.cmd.model.Order;

public class AddOrderCommand implements RestaurantCommand {

	private Order order;

	public AddOrderCommand(Order order) {
		this.order = order;
	}

	@Override
	public void execute() {
		this.order.processState();
		this.order.next();
		this.order.processState();
		this.order.next();
	}

}
