package lld.zomato.cmd.consts;

public enum OrderStatus {
	INIT("Order is getting initialized"),
	RECEIVED("Order is received"),
	PREPARING("Order is getting prepared"), 
	PREPARED("Order is ready"),
	DELIEVERING("Order is getting ready for delievery"), 
	OUT_FOR_DELIEVERY("Order is out for delievery"), 
	DELIEVERD("Order is delievered"),
	NOT_AVAILABLE("No such order exists");

	String description;
	
	OrderStatus(String string) {
		 this.description = string;
	}
	
	public String getDescription() {
		return description;
	}
	
}
