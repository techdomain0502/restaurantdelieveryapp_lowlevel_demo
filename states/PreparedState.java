package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.OrderDelieveryPickerTask;

public class PreparedState implements OrderState {

	@Override
	public void processState(Order order) {
		if (GlobalProvider.getGlobalProvider().getProcessedOrders().isEmpty()) {
			System.out.println("no orders in queue. \n" + "wrong input.\n please try again");
			return;
		}
		GlobalProvider.getGlobalProvider().getExecService().submit(
				new OrderDelieveryPickerTask(
               GlobalProvider.getGlobalProvider().getProcessedOrders(), 
              GlobalProvider.getGlobalProvider().getDelieveryOrders()));
		
	}

	@Override
	public void nextState(Order order) {
		 order.setState(new DelieveringState());
	}

 
}
