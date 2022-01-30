package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.OrderDelieveryPickerTask;
import lld.zomato.cmd.tasks.OrderDelieveryPlannerTask;

public class DelieveringState implements OrderState {

	@Override
	public void processState(Order order) {

		if (GlobalProvider.getGlobalProvider().getDelieveryOrders().isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		GlobalProvider.getGlobalProvider().getExecService()
				.submit(new OrderDelieveryPlannerTask(GlobalProvider.getGlobalProvider().getDelieveryOrders()));

	}

	@Override
	public void nextState(Order order) {
		order.setState(new DelieveredState());
	}

}
