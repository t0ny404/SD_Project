package sd.project.Service;

import sd.project.Service.DTO.IssueDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * The type Active cart, representing the active cart for the current user
 * It uses a Creational Design Pattern: Singleton
 */
public final class ActiveCart {

    private static final ActiveCart CART = new ActiveCart();

    private final Logger logger = LogManager.getLogger();
    private HashMap<IssueDTO, Integer> cart;


    private ActiveCart() {
        cart = new HashMap<>();
    }

    /**
     * Gets the current cart.
     *
     * @return the cart
     */
    public static ActiveCart getCart() {
        return CART;
    }


    /**
     * Add a current issue to the cart (as a new key with value 1 if not present
     * or increments the value if already present).
     *
     * @param key the IssueDTO as the key of the hashmap
     */
    public void add(IssueDTO key) {
        logger.info("Add 1 issue {} to cart", key.getTitle());
        cart.computeIfPresent(key, (k, v) -> v + 1);
        cart.putIfAbsent(key, 1);
    }

    /**
     * Remove a current issue (decrements the value in the hashmap and deletes it if the value is 0).
     *
     * @param key the key
     */
    public void remove(IssueDTO key) {
        logger.info("Remove 1 issue {} to cart", key.getTitle());
        cart.computeIfPresent(key, (k, v) -> v - 1);
        if (cart.get(key) == 0)
            cart.remove(key);
    }

    /**
     * Get the quantity added to the cart of a given issue.
     *
     * @param key the issue
     * @return the quantity of the given issue in the cart
     */
    public Integer get(IssueDTO key) {
        return cart.get(key);
    }


    /**
     * Gets all the issues in the cart.
     *
     * @return all the issues as List of IssueDTO
     */
    public List<IssueDTO> getAll() {
        logger.info("get all the issues in the cart");
        cart.forEach(IssueDTO::setQuantity);
        return new ArrayList<>(cart.keySet());
    }

    /**
     * Gets total.
     *
     * @return the total price
     */
    public Integer getTotal() {
        return getAll().stream().mapToInt(f -> f.getPrice() * f.getQuantity()).sum();
    }

    /**
     * Empties the cart.
     */
    public void empty() {
        cart = new HashMap<>();
        logger.info("empty the cart");
    }
}
