package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

/**
 * Session Bean implementation class OrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterface.class)
@LocalBean
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterface {

	private List<Order> orders;
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
    	
       	// Set orders 
		List<Order> ordersArr = new ArrayList<Order>();
		
		Order order1 = new Order("cats", "order1", 1.2f, 11);
		Order order2 = new Order("gum", "order2", 3.4f, 111);
		Order order3 = new Order("seeds", "order3", 5.6f, 1111);
		ordersArr.add(order1);
		ordersArr.add(order2);
		ordersArr.add(order3);
		
		setOrders(ordersArr);
		
    }

	/**
     * @see OrdersBusinessInterface#test()
     */
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
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
