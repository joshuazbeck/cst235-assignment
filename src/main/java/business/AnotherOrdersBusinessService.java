package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

/**
 * Session Bean implementation class AnotherOrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterface.class)
@LocalBean
@Alternative
public class AnotherOrdersBusinessService implements OrdersBusinessInterface {

	private List<Order> orders;
    /**
     * Default constructor. 
     */
    public AnotherOrdersBusinessService() {
       	// Set orders 
		List<Order> ordersArr = new ArrayList<Order>();
		
		Order order1 = new Order("dogs", "order1", 2.2f, 222);
		Order order2 = new Order("ice", "order2", 2.4f, 22);
		Order order3 = new Order("hotdogs", "order3", 2.6f, 2);
		ordersArr.add(order1);
		ordersArr.add(order2);
		ordersArr.add(order3);
		
		setOrders(ordersArr);
    }

	/**
     * @see OrdersBusinessInterface#test()
     */
    public void test() {
        System.out.println("Hello from the AnotherOrdersBusinessService");
    }

	@Override
	public List<Order> getOrders() {
		return this.orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
		
	}

}
