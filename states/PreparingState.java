package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.UnProcessedOrdersPickerTask;

public class PreparingState implements OrderState {

	@Override
	public void processState(Order order) {
	 
		GlobalProvider.getGlobalProvider().getExecService()
				.submit(new UnProcessedOrdersPickerTask(order,
						GlobalProvider.getGlobalProvider().getUnProcessedOrders(),
						GlobalProvider.getGlobalProvider().getProcessedOrders()));
        System.out.println("order no "+order.getId()+" is getting prepared");

	}

	@Override
	public void nextState(Order order) {
		order.setState(new PreparedState());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PREPARING";
	}

}
