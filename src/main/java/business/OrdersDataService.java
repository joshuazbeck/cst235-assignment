package business;

import beans.Order;
import beans.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class OrdersDataService
 */
@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public class OrdersDataService implements DataAccessInterface {

	List<Order> orders = new ArrayList<Order>();
    /**
     * Default constructor. 
     */
    public OrdersDataService() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Handle deleting the order
     * @param order
     */
    public void delete(Order order) {
    	Connection conn = null;
		try {
			//open connections
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			
			//add orders
			String sql = "DELETE FROM testapp.ORDERS WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, order.getId());
			statement.executeQuery(sql);
			conn.close();
			System.out.println("Successfully deleted order " + order.getId() + "!!");
		} catch (Exception e) {
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
    }
    /**
     * Update the orders
     * @param order
     */
    public void update(Order order) {
		Connection conn = null;
		try {
			//Open the connection
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			
			//Add the order
			String sql = "UPDATE testapp.ORDERS SET ORDER_NO=?, PRODUCT_NAME=?, PRICE=?, QUANTITY=? WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, order.getOrderNumber());
			statement.setString(2, order.getProductName());
			statement.setFloat(3, order.getPrice());
			statement.setInt(4, order.getQuantity());
			statement.setInt(5, order.getId());
			statement.executeQuery(sql);
			conn.close();
			System.out.println("Successfully updated order " + order.getId() + "!!");
		} catch (Exception e) {
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
    /**
     * Create the order
     * @param order
     */
    public void create(Order order) {
		Connection conn = null;
		try {
			//Open the connection
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			
			//Insert into the SQL
			String sql = "INSERT INTO testapp.ORDERS (ID, ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY)"
					+ " VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, order.getId());
			statement.setString(2, order.getOrderNumber());
			statement.setString(3, order.getProductName());
			statement.setFloat(4, order.getPrice());
			statement.setInt(5, order.getQuantity());
			
			statement.executeQuery(sql);
			conn.close();
			System.out.println("Successfully added order " + order.getId() + "!!");
		} catch (Exception e) {
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
    /**
     * Find the order
     * @param id - the ID to check for
     * @return - null object if no results
     */
    public Order findById(int id) {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			
			String sql = "SELECT * FROM testapp.ORDERS WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.executeQuery();
			Order o = null;
			while (rs.next()) {
				
				o.setId(id);
				o.setOrderNumber(rs.getString("ORDER_NO"));
				o.setProductName(rs.getString("PRODUCT_NUM"));
				o.setPrice(rs.getFloat("PRICE"));
				o.setQuantity(rs.getInt("QUANTITY"));
			}
			
			conn.close();
			System.out.println("Successfully added order " + id + "!!");
			return o;
		} catch (Exception e) {
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
    /**
     * Return all the objects
     */
	public List<Order> findAll() {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			Statement statement = conn.createStatement();
			String sql = "SELECT * FROM testapp.ORDERS";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String order_no = rs.getString("ORDER_NO");
				String productName = rs.getString("PRODUCT_NAME");
				float price = rs.getFloat("PRICE");
				int quantity = rs.getInt("QUANTITY");
				Order order = new Order(id, order_no, productName, price, quantity);
				this.orders.add(order);
			}
			conn.close();
			System.out.println("Success!!");
		} catch (Exception e) {
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return this.orders;
	}
}
