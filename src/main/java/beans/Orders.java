package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Orders {
	List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Orders() {
		orders = new ArrayList<Order>();
		
		Order order1 = new Order("abc", "order1", 1.2f, 11);
		Order order2 = new Order("cba", "order2", 3.4f, 111);
		Order order3 = new Order("def", "order3", 5.6f, 1111);
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);
	}
}
