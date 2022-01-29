package lld.zomato.cmd;

import java.util.Scanner;

import lld.zomato.cmd.commands.ShowMenuCommand;
import lld.zomato.cmd.model.Order;

public class MainApp {

	public static void main(String[] args) throws InterruptedException {
		int id = 1;
		OrderProcessorService service = new OrderProcessorService();
		Scanner sc = new Scanner(System.in);
		int input = -1;
		new ShowMenuCommand().execute();
		while (true) {

			if (!sc.hasNextInt()) {
				System.out.println("invalid Input. please try again!!");
				sc.next();
				continue;
			}
			input = sc.nextInt();

			switch (input) {
			case 1:
				service.addOrder(new Order(id, OrderStatus.INIT));
				id++;
				break;
			case 2:
				service.preparingOrder();
				break;
			case 3:
				service.preparedOrder();
				break;
			case 4:
				service.delieverOrder();
				break;
			case 5:
				service.getOrderCurrentStatus();
				break;
			case 6:
				service.shutDownRestaurant();
				return;
			case 7:
				service.cancelOrder();
				break;
			case 8:
				service.getPendingOrderList();
				break;
			case -1: {
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
