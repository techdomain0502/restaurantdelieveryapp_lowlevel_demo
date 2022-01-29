package lld.zomato.cmd.commands;

public class ShutDownRestaurantCommand implements RestaurantCommand {

	@Override
	public void execute() {
		GlobalProvider.getGlobalProvider().getExecService().shutdown();
		System.out.println("shutting down restaurant for the day!!");
	}

}
