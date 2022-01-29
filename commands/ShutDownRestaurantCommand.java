package lld.zomato.cmd.commands;

import lld.zomato.cmd.providers.GlobalProvider;

public class ShutDownRestaurantCommand implements RestaurantCommand {

	@Override
	public void execute() {
		GlobalProvider.getGlobalProvider().getExecService().shutdown();
		System.out.println("shutting down restaurant for the day!!");
	}

}
