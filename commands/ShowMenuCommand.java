package lld.zomato.cmd.commands;

public class ShowMenuCommand implements RestaurantCommand{

	@Override
	public void execute() {
		System.out.println("\n****************************\n" + 
	           "welcome to xyz restaurant\n"
				+ "please choose from below . press number \n" + 
	           "1.add order" // its
				+ "\n2.prepare order" // for restaurant api
				+ "\n3.prepared order"// for restaurant api
				+ "\n4.deliever order"// for restaurant api
				+ "\n5.get current status of order" // its also endpoint for
													// customer to get his order status
				+ "\n6.shutdown- close restaurant" + ""
						+ "\n7. Cancel order"
						+ "\n8. pending order list"
						+ "\n-1.show command menu again\n****************************\n");// for restaurant api	
	}

}
