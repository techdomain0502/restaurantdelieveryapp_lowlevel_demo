package lld.zomato.cmd.states;

import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.OrderDelieveryPlannerTask;

public class DelieveredState implements OrderState {

	@Override
	public void processState(Order order) {

		order.setStatus(OrderStatus.DELIEVERD);
		OrdersDatabase.getInstance().setCurrentStatus(order.getId(), order.getStatus());
		System.out.println("order# " + order.getId()+" "+order.getStatus().getDescription());
	}

	@Override
	public void nextState(Order order) {
        System.out.println("Please do visit us again :)");   
	}

}
