package lld.zomato.cmd.model;

import lld.zomato.cmd.consts.OrderStatus;
import lld.zomato.cmd.states.InitState;
import lld.zomato.cmd.states.OrderState;

public class Order {
	private int id;
	private OrderStatus status;
	private OrderState currentState;

	public Order(int id, OrderStatus state) {
		super();
		this.id = id;
		this.status = state;
		this.currentState = new InitState();
	}

	public void setState(OrderState state) {
		this.currentState = state;
	}

	public void next() {
		this.currentState.nextState(this);
	}

	public void processState(Order order) {
		this.currentState.processState(order);
	}

	public int getId() {
		return id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "order id = " + id + " state=" + status;
	}
}
