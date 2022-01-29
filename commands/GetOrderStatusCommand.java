package lld.zomato.cmd.commands;

import java.util.Scanner;

import lld.zomato.cmd.OrdersDatabase;
import lld.zomato.cmd.Status;

public class GetOrderStatusCommand implements RestaurantCommand {

	@Override
	public void execute() {
		System.out.println("Enter order#");
		Scanner sc = GlobalProvider.getGlobalProvider().getScanner();
		int id = sc.nextInt();
		Status s = OrdersDatabase.getInstance().getCurrentStatus(id);
		System.out.println("Status of order# " + id + " " + s);
	}

}
