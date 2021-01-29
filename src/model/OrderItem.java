package model;

public class OrderItem {
	
	private static long lastItemId = 1L; // initial item ID

    private long itemId;
    private Product product;
    private int quantity;

    /**
     * Creates a default instance of the OrderItem class.
     * 
     * @param product the product being purchased
     */

    public OrderItem(Product product) {
        this.itemId = OrderItem.lastItemId++; // auto-generate ID
        this.product = product;
        this.quantity = 1;
    }

    /**
     * Creates an instance of the OrderItem class.
     * 
     * @param itemId   the item ID
     * @param product  the product being purchased
     * @param quantity the quantity being purchased
     */

    public OrderItem(long itemId, Product product, int quantity) {
        this.itemId = itemId;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Returns the price based on quantity and product unit price.
     * 
     * @return the price
     */

    public double getPrice() {
        return quantity * product.getUnitPrice();
    }
    
    /**
     * Returns the item ID.
     *
     * @return itemId
     */

    public long getItemId() {
        return itemId;
    }
    
    /**
     * Returns the Product.
     *
     * @return product
     */

    public Product getProduct() {
        return product;
    }
    
    /**
     * Returns the quantity.
     *
     * @return quantity
     */

    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Sets the quantity.
     *
     * @param quantity the new quantity
     */

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setItemId(long id) {
    	this.itemId = id;
    }

}
