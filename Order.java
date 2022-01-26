package lld.zomato.cmd;

public class Order {
   int id;
   Status state;
   

public Order(int id, Status state) {
	super();
	this.id = id;
	this.state = state;
}


@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "order id = "+id+" state="+state;
	}
}
