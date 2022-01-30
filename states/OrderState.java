package lld.zomato.cmd.states;

import lld.zomato.cmd.model.Order;

public interface OrderState {
  public void processState(Order order);
  public  void nextState(Order order);
}
