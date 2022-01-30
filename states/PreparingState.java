package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.UnProcessedOrdersPickerTask;

public class PreparingState implements OrderState {

	@Override
	public void processState(Order order) {
		if (GlobalProvider.getGlobalProvider().getUnProcessedOrders().isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		GlobalProvider.getGlobalProvider().getExecService()
				.submit(new UnProcessedOrdersPickerTask(
						GlobalProvider.getGlobalProvider().getUnProcessedOrders(),
						GlobalProvider.getGlobalProvider().getProcessedOrders()));

	}

	@Override
	public void nextState(Order order) {
		order.setState(new PreparedState());
	}

}
