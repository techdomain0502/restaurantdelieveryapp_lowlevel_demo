package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;

public class DelieveredState implements OrderState {

	@Override
	public void processState(Order order) {

		OrdersDatabase.getInstance().setCurrentStatus(order.getId(), order.getCurrentState());
        System.out.println("order no "+order.getId()+" is delievered");

	}

	@Override
	public void nextState(Order order) {
        System.out.println("Please do visit us again :)");   
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DELIEVERED";
	}

}
