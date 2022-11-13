package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
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
	
	@EJB
	private OrdersDataService service;
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
    	
   
    }

	/**
     * @see OrdersBusinessInterface#test()
     */
    public void test() {
        System.out.println("Hello from the OrdersBusinessService");
    }
    

	@Override
	public List<Order> getOrders() {
		return service.findAll();
	}


}
