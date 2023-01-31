package sandbox;

import java.util.WeakHashMap;

public class Customer {
    private String password;
    private String email;

    private static WeakHashMap<Object, Customer> instance;
    private static Object dummy = new Object();

    public Customer(String password, String email) {
        if (instance.values().stream().anyMatch(customer -> customer.email.equalsIgnoreCase(email))) {
            throw new IllegalArgumentException();
        }
        this.password = password;
        this.email = email;
        instance.put(dummy, this);
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
