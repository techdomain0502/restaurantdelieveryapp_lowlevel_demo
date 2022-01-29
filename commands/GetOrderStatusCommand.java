package lld.zomato.cmd.commands;

import java.util.Scanner;

import lld.zomato.cmd.OrderStatus;
import lld.zomato.cmd.model.OrdersDatabase;
import lld.zomato.cmd.providers.GlobalProvider;

public class GetOrderStatusCommand implements RestaurantCommand {

	@Override
	public void execute() {
		System.out.println("Enter order#");
		Scanner sc = GlobalProvider.getGlobalProvider().getScanner();
		int id = sc.nextInt();
		OrderStatus s = OrdersDatabase.getInstance().getCurrentStatus(id);
		System.out.println("Status of order# " + id + " " + s);
	}

}
