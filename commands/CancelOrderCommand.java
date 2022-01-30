package lld.zomato.cmd.commands;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;
import lld.zomato.cmd.providers.GlobalProvider;

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
		String status = OrdersDatabase.getInstance().getCurrentStatus(orderId);
		OrdersDatabase.getInstance().removeOrder(orderId);

		if (status.equalsIgnoreCase("RECEIVED")) {
			Iterator<Order> iterator = GlobalProvider.getGlobalProvider().getUnProcessedOrders().iterator();

			while (iterator.hasNext()) {
				Order o = iterator.next();
				if (o.getId() == orderId) {
					iterator.remove();
					break;
				}
			}

			System.out.println("order with id " + orderId + " is cancelled");
			return;
		}

		System.out.println("sorry order is state " + status + " and can't be cancelled now");
	}

}
