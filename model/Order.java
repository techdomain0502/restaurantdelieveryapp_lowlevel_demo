package lld.zomato.cmd.model;

import lld.zomato.cmd.OrderStatus;

public class Order {
   private int id;
   private OrderStatus status;
   

public Order(int id, OrderStatus state) {
	super();
	this.id = id;
	this.status = state;
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
		return "order id = "+id+" state="+status;
	}
}
