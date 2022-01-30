package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;

public class InitState implements OrderState {


	@Override
	public void nextState(Order order) {
		order.setState(new ReceivedState());
	}

	@Override
	public void processState(Order order) {
        System.out.println("order initialized  id "+order.getId()); 		
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "INIT";
	}
	 

}
