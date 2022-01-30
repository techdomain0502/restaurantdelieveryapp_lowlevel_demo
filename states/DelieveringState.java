package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.OrderDelieveryPickerTask;
import lld.zomato.cmd.tasks.OrderDelieveryPlannerTask;

public class DelieveringState implements OrderState {

	@Override
	public void processState(Order order) {
 
		GlobalProvider.getGlobalProvider().getExecService()
				.submit(new OrderDelieveryPlannerTask(order,GlobalProvider.getGlobalProvider().getDelieveryOrders()));
        System.out.println("order no "+order.getId()+" is scheduled for delievery");

	}

	@Override
	public void nextState(Order order) {
		order.setState(new DelieveredState());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DELIEVERING";
	}

}
