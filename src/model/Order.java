package model;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {

	private static long lastOrderId = 1L; // initial order ID

	private long orderId;
	private Customer customer;
	private long orderDate;
	private String status;
	private ArrayList<OrderItem> items;
	private double total;

	/**
	 * Creates a default instance of the Order class.
	 */

	public Order() {
		this.orderId = Order.lastOrderId++; // auto-generate ID
		this.customer = new Customer();
		this.orderDate = getDate();
		this.status = "O";
		this.items = new ArrayList<>();
		this.total = 0;
	}

	/**
	 * Creates an instance of the Order class.
	 * 
	 * @param orderId   the order ID
	 * @param customer  the customer who placed the order
	 * @param orderDate the date the order was placed
	 * @param status    the status of the order
	 * @param items     the items included in the order
	 * @param total     the total price of the order
	 */

	public Order(long orderId, Customer customer, long orderDate, String status, ArrayList<OrderItem> items,
			double total)
	{
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.status = status;
		this.items = items;
		this.total = total;
	}

	/**
	 * Adds an OrderItem to the Order.
	 * 
	 * @param item the item to be added
	 */

	public void addItem(OrderItem item) {
		if (items == null) {
			items = new ArrayList<>();
		}

		items.add(item);
		this.updateTotal();
	}

	/**
	 * Removes an OrderItem from the Order.
	 * 
	 * @param item the item to be removed
	 */

	public void removeItem(OrderItem item) {
		if (items != null) {
			items.remove(item);
		}

		this.updateTotal();
	}

	/**
	 * Returns the order ID.
	 *
	 * @return orderId
	 */

	public long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(long id) {
		this.orderId = id;
	}

	/**
	 * Returns the Customer.
	 *
	 * @return customer
	 */

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Returns the order date.
	 *
	 * @return orderDate
	 */

	public long getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Returns the order status.
	 *
	 * @return status
	 */

	public String getStatus() {
		return status;
	}

	/**
	 * Sets the order status.
	 *
	 * @param status the new status
	 */

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns the list of OrderItems.
	 *
	 * @return items
	 */

	public ArrayList<OrderItem> getItems() {
		return items;
	}

	/**
	 * Returns the order total.
	 *
	 * @return total
	 */

	public double getTotal() {
		return Math.round(total*100)/100;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}

	/*
	 * Gets the current date in YYYYMMDD format.
	 */

	private long getDate() {
		LocalDateTime now = LocalDateTime.now();

		String year = String.format("%d", now.getYear());
		String month = String.format("%02d", now.getMonthValue());
		String day = String.format("%02d", now.getDayOfMonth());

		return Long.valueOf(year + month + day);
	}

	/*
	 * Updates the order total using the price of each item.
	 */

	private void updateTotal() {
		if (items == null) {
			this.total = 0;
		} else {
			double total = 0;

			for (OrderItem item : items) {
				total = total + item.getPrice();
			}

			this.total = total;
		}
	}

}
