package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.OrderDelieveryPickerTask;

public class PreparedState implements OrderState {

	@Override
	public void processState(Order order) {

		GlobalProvider.getGlobalProvider().getExecService()
				.submit(new OrderDelieveryPickerTask(order, 
						GlobalProvider.getGlobalProvider().getProcessedOrders(),
						GlobalProvider.getGlobalProvider().getDelieveryOrders()));
        System.out.println("order no "+order.getId()+" is prepared");

	}

	@Override
	public void nextState(Order order) {
		order.setState(new DelieveringState());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PREPARED";
	}

}
