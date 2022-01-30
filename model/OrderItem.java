package lld.zomato.cmd.model;

public class OrderItem {
	int id;
	String state;

	public int getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", state=" + state + "]";
	}
	
	

}
