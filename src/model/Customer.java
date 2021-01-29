package model;

public class Customer {
	
	public static long lastCustomerId = 1L; // initial customer ID

    private long customerId;
    private String firstName;
    private String lastName;
    private long phone;
    private String email;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;

    /**
     * Creates a default instance of the Customer class.
     */

    public Customer() {
        this.customerId = Customer.lastCustomerId++;
        this.firstName = "John";
        this.lastName = "Doe";
        this.phone = 1234567890;
        this.email = "jdoe@ucvts.org";
        this.streetAddress = "1600 Pennsylvania Avenue";
        this.city = "Seattle";
        this.state = "WA";
        this.postalCode = "12345";
    }

    /**
     * Creates an instance of the Customer class.
     * 
     * @param firstName     the first name
     * @param lastName      the last name
     * @param phone         the telephone number
     * @param email         the email address
     * @param streetAddress the street address
     * @param city          the city
     * @param state         the state
     * @param postalCode    the postal code
     */

    public Customer(long customerId, String firstName, String lastName, long phone, String email, String streetAddress,
            String city, String state, String postalCode)
    {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    /**
     * Returns the customer ID.
     *
     * @return customerId
     */

    public long getCustomerId() {
        return customerId;
    }
    
    /**
     * Returns the customer's first name.
     *
     * @return firstName
     */
    
    public void setCustomerId(long id) {
    	this.customerId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer's first name.
     *
     * @param firstName the new first name
     */
     
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Returns the customer's last name.
     *
     * @return lastName
     */

    public String getLastName() {
        return lastName;
    }
    
    /**
     * Sets the customer's last name.
     *
     * @param lastName the new last name
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Returns the customer's phone number.
     *
     * @return phone
     */
     
    public long getPhone() {
        return phone;
    }
    
    /**
     * Sets the customer's phone number.
     *
     * @param phone the new phone number
     */

    public void setPhone(long phone) {
        this.phone = phone;
    }
    
    /**
     * Returns the customer's email address.
     *
     * @return email
     */
     
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets the customer's email address.
     *
     * @param email the new email address
     */

    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns the customer's street address.
     *
     * @return streetAddress
     */

    public String getStreetAddress() {
        return streetAddress;
    }
    
    /**
     * Sets the customer's street address.
     *
     * @param streetAddress the new street address
     */

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    
    /**
     * Returns the city in which the cutomer lives.
     *
     * @return city
     */

    public String getCity() {
        return city;
    }
    
    /**
     * Sets the city in which the customer lives.
     *
     * @param city the new city
     */

    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Returns the state in which the customer lives.
     *
     * @return state
     */

    public String getState() {
        return state;
    }
    
    /**
     * Sets the state in which the customer lives.
     *
     * @param state the new state
     */

    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * Returns the postal code in which the customer lives.
     *
     * @return postalCode
     */

    public String getPostalCode() {
        return postalCode;
    }
    
    /**
     * Sets the postal code in which the customer lives.
     *
     * @param postalCode the new postalCode
     */

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
