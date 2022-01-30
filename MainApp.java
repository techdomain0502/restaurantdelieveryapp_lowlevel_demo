package lld.zomato.cmd;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import lld.zomato.cmd.commands.ShowMenuCommand;
import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.states.InitState;

public class MainApp {

	public static void main(String[] args) throws InterruptedException {
		int id = 1;
		OrderProcessorService service = new OrderProcessorService();
		Scanner sc = new Scanner(System.in);
		int input = -1;
		new ShowMenuCommand().execute();
		Order order = null;
		while (true) {

			if (!sc.hasNextInt()) {
				System.out.println("invalid Input. please try again!!");
				sc.next();
				continue;
			}
			input = sc.nextInt();

			Command c = Command.getCommandByOrdinal(input);
			switch (c) {
			case ADD_ORDER:
				order = new Order(id, OrderStatus.INIT);
				order.setState(new InitState());
				service.addOrder(order);
				id++;
				break;
			case PREPARE_ORDER:
				Optional.ofNullable(order).ifPresentOrElse(or -> service.preparingOrder(or),
						() -> System.out.println("order not initialized. please try again"));
				break;
			case SET_ORDER_READY:
				Optional.ofNullable(order).ifPresentOrElse(or -> service.preparedOrder(or),
						() -> System.out.println("order not initialized. please try again"));
				break;
			case DELIVERED_ORDER:
				Optional.ofNullable(order).ifPresentOrElse(or -> service.delieverOrder(or),
						() -> System.out.println("order not initialized. please try again"));
				break;
			case GET_ORDER_STATUS:
				service.getOrderCurrentStatus();
				break;
			case SHUTDOWN_RESTAURANT:
				service.shutDownRestaurant();
				return;
			case CANCEL_ORDER:
				service.cancelOrder();
				break;
			case SHOW_ORDERS:
				service.getPendingOrderList();
				break;
			case SHOW_RESTAURANT_MENU: {
				service.showMenu();
			}
				break;
			default:
				System.out.println("invalid input. please try again");
				continue;
			}

		}
	}

}
