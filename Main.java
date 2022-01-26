package lld.zomato.cmd;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int id = 1;
		OrdersService service = new OrdersService();
		Scanner sc = new Scanner(System.in);
		String input = "";

		while (true) {
			input = sc.next();
			input = input.toLowerCase();
			switch (input) {
			case "ad":
				service.addOrder(new Order(id, Status.INIT));
				id++;
				break;
			case "pg":
				service.preparingOrder();
				break;
			case "pd":
				service.preparedOrder();
				break;
			case "dl":
				service.delieverOrder();
				break;
			case "gcs":
				service.getOrderCurrentStatus();
				break;
			case "shutdown":
				service.shutDownRestaurant();
				return;
			}
		}

	}
}
