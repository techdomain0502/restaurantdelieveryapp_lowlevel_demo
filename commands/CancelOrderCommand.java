package lld.zomato.cmd.commands;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.Order;
import lld.zomato.cmd.OrdersDatabase;
import lld.zomato.cmd.Status;

public class CancelOrderCommand implements RestaurantCommand {

	private BlockingQueue<Order> unProcessedOrders;
	
	
	
	public CancelOrderCommand() {
		super();
		unProcessedOrders = GlobalProvider.getGlobalProvider().getUnProcessedOrders();
	}



	@Override
	public void execute() {
		System.out.println("please enter the order no to cancel!!");
		int orderId = GlobalProvider.getGlobalProvider().getScanner().nextInt();
		Status currentStatus = OrdersDatabase.getInstance().getCurrentStatus(orderId);
		if (currentStatus == Status.INIT || currentStatus == Status.RECEIVED) {
			Order order = OrdersDatabase.getInstance().removeOrder(orderId);
			Optional.ofNullable(order).ifPresent(orde -> {
				unProcessedOrders.remove(orde);
				System.out.println("order # " + orderId + " CANCELLED. SEE YOU AGAIN:)");
			});
		} else {
			System.out.println("sorrry your order is in state " + currentStatus + "\n cannot be cancelled now");
		}
	}

}
