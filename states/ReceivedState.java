package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;
import lld.zomato.cmd.model.OrdersDatabase;
import lld.zomato.cmd.providers.GlobalProvider;
import lld.zomato.cmd.tasks.UnProcessedOrderPutterTask;

public class ReceivedState implements OrderState {

	@Override
	public void processState(Order order) {
		OrdersDatabase.getInstance().addOrder(order);
		GlobalProvider.getGlobalProvider().getExecService().submit(
				new UnProcessedOrderPutterTask(order, GlobalProvider.getGlobalProvider().getUnProcessedOrders()));
         System.out.println("order no "+order.getId()+" received");
	}

	@Override
	public void nextState(Order order) {
		order.setState(new PreparingState());
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RECEIVED";
	}
}
