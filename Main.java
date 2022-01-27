package lld.zomato.cmd;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int id = 1;
		OrdersService service = new OrdersService();
		Scanner sc = new Scanner(System.in);
		int input = -1;

		while (true) {
			try {
				System.out.println(
						     "\n****************************\n"
						     + "welcome to xyz restaurant\n" + 
				              "please choose from below . press number \n" 
								+ "1.add order" // its
								+ "\n2.prepare order" // for restaurant api
								+ "\n3.prepared order"// for restaurant api
								+ "\n4.deliever order"// for restaurant api
								+ "\n5.get current status of order" // its also endpoint for
																	// customer to get his order status
								+ "\n6.shutdown- close restaurant\n"
								+"\n****************************\n");// for restaurant api
				input = sc.nextInt();
				switch (input) {
				case 1:
					service.addOrder(new Order(id, Status.INIT));
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
					default:
						continue;
				}
			} catch (Exception e) {
				System.out.println("invalid entry. please try again");
				sc.next();
			}

		}
	}
}
