package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Orders;
import beans.User;

/**
 * The controller in charge of manipulating the FormController
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class FormController {
	
	//Code that is executed on form submit
	public String onSubmit() {
		
		// Get the users from the input form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Inject the user into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		Orders orders = new Orders();
		// Inject the orders into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("orders", orders.getOrders());

		return "TestResponse.xhtml";
	}
	
	//Code submitted to cause a redirect on press
	public String onFlash(User user) {
		// Inject the user into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);

		return "TestResponse2.xhtml?faces-redirect=true";
	}
	
}
