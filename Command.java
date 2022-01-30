package lld.zomato.cmd;

import java.util.Arrays;
import java.util.Optional;

public enum Command {
	ADD_ORDER(10), PREPARE_ORDER(20), SET_ORDER_READY(30), DELIVERED_ORDER(40), GET_ORDER_STATUS(50),
	SHOW_RESTAURANT_MENU(60), SHUTDOWN_RESTAURANT(70), CANCEL_ORDER(80), SHOW_ORDERS(90), INVALID_COMMAND(-1);

	private int command_no;

	private Command(int i) {
		this.command_no = i;
	}

	public int getCommandNo() {
		return command_no;
	}

	public static Command getCommandByOrdinal(int value) {
		Optional<Command> commandOptional = Arrays.stream(values()).filter(c -> c.command_no == value).findFirst();
		if (commandOptional.isPresent()) {
			return commandOptional.get();
		} else
			return INVALID_COMMAND;
	}
}
