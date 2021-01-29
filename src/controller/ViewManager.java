package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GUI.MidtownComics;
import dao.CustomerDAO;
import dao.DAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.Product;
import views.CartView;
import views.InventoryView;
import views.OrderListView;
import views.OrderView;
import views.PaymentView;
import views.ProductView;
import views.CustomerView;
import views.ClientsView;

public class ViewManager {

    private static ViewManager manager;
    
    private Container views;
    private Order order;
    

    private ViewManager(Container views) {
        this.views = views;
   
        
        try {
            DAO.buildDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    public static ViewManager getInstance(Container views) {
        if (manager == null) {
            manager = new ViewManager(views);
        }

        return manager;
    }
    
    

    public void switchTo(String view) {
        ((CardLayout) views.getLayout()).show(views, view);
    }
    
    
    
    public void attachProduct(Product product) {
        ((ProductView) views.getComponent(MidtownComics.ProductViewIndex)).setProduct(product);
    }
    
    public void attachCustomer(Customer customer) {
    	((CustomerView) views.getComponent(MidtownComics.CustomerViewIndex)).setCustomer(customer);
    }
    
    public void attachOrder(Order order) {
    	((OrderView) views.getComponent(MidtownComics.OrderViewIndex)).setOrder(order);
    }
    
    
    
    public void detachProduct() {
        ((ProductView) views.getComponent(MidtownComics.ProductViewIndex)).setProduct(null);
    }
    
    public void detachCustomer() {
    	((CustomerView) views.getComponent(MidtownComics.CustomerViewIndex)).setCustomer(null);
    }
    
    public void detachOrder() {
    	((OrderView) views.getComponent(MidtownComics.OrderViewIndex)).setOrder(null);
    }
    
    
    

    public void addProductToInventory(Product product) {
        try {            
            ProductDAO.insertProduct(product);
    
            detachProduct();
            refreshInventoryList();
            switchTo(MidtownComics.InventoryView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addCustomerToClients(Customer customer) {
    	try {
    		CustomerDAO.insertCustomer(customer);
    		
    		detachCustomer();
    		refreshClientsList();
    		switchTo(MidtownComics.CustomerView);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void addOrderToOrderList(Order order) {
    	try {
    		OrderDAO.insertOrder(order);
    		
    		detachOrder();
    		refreshOrderList();
    		switchTo(MidtownComics.OrderView);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    

    public void modifyProductInInventory(Product product) {
        try {
            ProductDAO.updateProduct(product);
            
            detachProduct();
            refreshInventoryList();
            switchTo(MidtownComics.InventoryView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void modifyCustomerInClients(Customer customer) {
        try {
            CustomerDAO.updateCustomer(customer);
            
            detachCustomer();
            refreshClientsList();
            switchTo(MidtownComics.ClientsView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void modifyOrderInOrderList(Order order) {
    	try {
    		OrderDAO.updateOrder(order);
    		
    		detachOrder();
    		refreshOrderList();
    		switchTo(MidtownComics.OrderView);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    

    public void removeProductFromInventory(Product product) {
        try {
            ProductDAO.deleteProduct(product);
    
            detachProduct();
            refreshInventoryList();
            switchTo(MidtownComics.InventoryView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void removeCustomerFromClients(Customer customer) {
        try {
            List<Order> deleteOrders = OrderDAO.getOrders(customer.getCustomerId());
            for (int i = 0; i < deleteOrders.size(); i++) {
            	List<OrderItem> deleteItems = OrderItemDAO.getOrderItems(deleteOrders.get(i).getOrderId(), ProductDAO.getProducts());
            	for (int j = 0; j < deleteItems.size(); j++) {
            		OrderItemDAO.deleteOrderItem(deleteItems.get(j));
            	}
            	OrderDAO.deleteOrder(deleteOrders.get(i));
            }
        	CustomerDAO.deleteCustomer(customer);
    
            detachCustomer();
            refreshClientsList();
            refreshOrderList();
            switchTo(MidtownComics.ClientsView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void removeOrderFromOrderList(Order order) {
    	try {
    		OrderDAO.deleteOrder(order);
    		
    		detachOrder();
    		refreshOrderList();
    		switchTo(MidtownComics.OrderView);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    

    public void addItemToOrder(OrderItem item) throws SQLException {
        if (order == null) {
            order = new Order();
        }

        order.addItem(item);
        refreshOrderList();
    
    }

    

    public void modifyItemQuantityInOrder(Product product, int quantity) {
        int index = findItemInOrder(product);
        order.getItems().get(index).setQuantity(quantity);        

        refreshCart();
        switchTo(MidtownComics.InventoryView);
        switchTo(MidtownComics.CartView);
    }

    

    public void removeItemFromOrder(Product product) {
        int index = findItemInOrder(product);
        order.getItems().remove(index);

        refreshCart();
        switchTo(MidtownComics.InventoryView);
        
        if (order.getItems().size() > 0) {
            switchTo(MidtownComics.CartView);
        }
    }
    
    
    
    public int getOrderItemQuantity(Product product) {
        int index = findItemInOrder(product);
        
        return index != -1 ? order.getItems().get(index).getQuantity() : 0;
    }
    
    
    
    public double getSubtotal(Product product) {
        int index = findItemInOrder(product);
        
        return index != -1 ? (order.getItems().get(index).getPrice()) : 0;
    }
    
    public void updateTotal() {
    	((PaymentView) views.getComponent(MidtownComics.PaymentViewIndex)).updateOrderTotal(order.getTotal());
    }
    
    
    
    public boolean productExistsInOrder(Product product) {
        if (order == null) {
            return false;
        }
        
        return findItemInOrder(product) != -1;
    }
    
    
    
    public void submitOrder() throws SQLException {
        CustomerDAO.insertCustomer(order.getCustomer()); 
        OrderDAO.insertOrder(order);
        for (int i = 0; i < order.getItems().size(); i++) {
            OrderItem item = order.getItems().get(i);
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            int copies = product.getCopies();
            
            product.setCopies(copies - quantity);
            modifyProductInInventory(product);
            OrderItemDAO.insertOrderItem(item, order);
            order.setStatus("C");
            refreshOrderList();
            refreshClientsList();
        }
        
  
        
        order = null;
    }

    
    
    public List<Product> getInventory() {
        try {
            return ProductDAO.getProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new ArrayList<>();
    }
    
    public List<Customer> getCustomers() {
    	try {
    		return CustomerDAO.getCustomers();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return new ArrayList<>();
    }
    
    public List<Order> getOrders() {
    	try {
    		return OrderDAO.getOrders();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return new ArrayList<>();
    }
    
    
    
    public Order getOrder() {
        return order;
    }

    
    
    private void refreshInventoryList() {
        ((InventoryView) views.getComponent(MidtownComics.InventoryViewIndex)).refreshInventoryList();
    }
    
    private void refreshClientsList() {
    	((ClientsView) views.getComponent(MidtownComics.ClientsViewIndex)).refreshClientsList();
    }
    
    private void refreshOrderList() {
    	((OrderListView) views.getComponent(MidtownComics.OrderListViewIndex)).refreshOrderList();
    }
    
    
    
    public void refreshCart() {
        ((CartView) views.getComponent(MidtownComics.CartViewIndex)).refreshCart();
    }

    
    
    private int findItemInOrder(Product product) {
        for (int i = 0; i < order.getItems().size(); i++) {
            if (order.getItems().get(i).getProduct().getProductId() == product.getProductId()) {
                return i;
            }
        }

        return -1;
    }
}