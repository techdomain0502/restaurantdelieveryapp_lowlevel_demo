package lld.zomato.cmd.model;

import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.states.OrderState;

public class Order {
	private int id;
	private OrderState currentState;

	public Order(int id) {
		super();
		this.id = id;
	}

	public void setState(OrderState state) {
		this.currentState = state;
	}

	public void next() {
		this.currentState.nextState(this);
	} 

	public void processState() {
		this.currentState.processState(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "order id = " + id + " state=" + currentState;
	}

	public void setStatus(OrderStatus currStatus) {
		  
		
	}

	public OrderState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(OrderState currentState) {
		this.currentState = currentState;
	}
}
