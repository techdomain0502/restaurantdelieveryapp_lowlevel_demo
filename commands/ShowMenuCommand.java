package lld.zomato.cmd.commands;

import java.util.Arrays;

import lld.zomato.cmd.Command;

public class ShowMenuCommand implements RestaurantCommand {

	@Override
	public void execute() {
		System.out.println("please choose from below optoins");
		Arrays.stream(Command.values()).filter(c -> c.getCommandNo() != -1)
				.forEach(c -> System.out.println(c.getCommandNo() + " " + c.name()));
	}

}
