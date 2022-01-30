package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;

public class ErrorState implements OrderState {

	@Override
	public void processState(Order order) {
	 
		System.out.println("Order is not in consistent state");
		
	}

	@Override
	public void nextState(Order order) {
		
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "error met";
	}

}
