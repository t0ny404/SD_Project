package sd.project.Service;

import sd.project.Model.Cart;
import sd.project.Model.Issue;
import sd.project.Model.Order;
import sd.project.Model.Utils.Status;
import sd.project.Repository.*;
import sd.project.Service.DTO.IssueDTO;
import sd.project.Service.DTO.OrderDTO;
import sd.project.Service.Mappers.IssueMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Order service, handling all the logic for the orders.
 */
@Service
public class OrderService {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;


    /**
     * Makes a new order adding it to the database and updates the current cart for given user.
     *
     * @param user the user id
     */
    public void order(Integer user) {
        logger.info("Current total: {}", ActiveCart.getCart().getTotal());

        Order order = orderRepository.save(new Order(
                customerRepository.findById(user),
                Status.PENDING,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                ActiveCart.getCart().getTotal()));
        logger.info("Current date {}", order.getDate());

        for (IssueDTO issueDTO : ActiveCart.getCart().getAll()) {
            Issue issue = new IssueMapper().convertFromDTO(issueDTO);
            issue.setId(issueDTO.getId());

            logger.info("Issue {} added to cart", issue.getTitle());
            cartRepository.save(new Cart(
                    issue,
                    order,
                    issueDTO.getQuantity()));
        }
    }

    /**
     * Gets the history of all completed (i.e. with status Declined or Delivered) orders of the given user.
     *
     * @param user the user id
     * @return the history of all orders of the given user
     */
    public List<OrderDTO> getHistory(Integer user) {
        logger.info("History orders for user {}", user);
        return orderRepository.findByCustomerAndStatusIn(
                customerRepository.findById(user),
                new Status[]{Status.DECLINED, Status.DELIVERED})
                .stream().map(o -> new OrderDTO(
                        o.getId(),
                        cartRepository.findByOrder(o)
                                .stream().map(c -> c.getIssue().getTitle()).collect(Collectors.toList()).toString(),
                        o.getPrice(),
                        o.getDate(),
                        o.getStatus().name()))
                .collect(Collectors.toList());
    }

    /**
     * Gets the current orders of the given user (i.e. with status Pending)
     *
     * @param user the user id
     * @return the current orders of the given user
     */
    public List<OrderDTO> getCurrent(Integer user) {
        logger.info("Pending orders for user {}", user);
        return orderRepository.findByCustomerAndStatusNotIn(
                customerRepository.findById(user),
                new Status[]{Status.DECLINED, Status.DELIVERED})
                .stream().map(o -> new OrderDTO(
                        o.getId(),
                        cartRepository.findByOrder(o)
                                .stream().map(c -> c.getIssue().getTitle()).collect(Collectors.toList()).toString(),
                        o.getPrice(),
                        o.getDate(),
                        o.getStatus().name()))
                .collect(Collectors.toList());
    }

    /**
     * Gets all the orders.
     *
     * @return all the orders as a List of OrderDTO
     */
    public List<OrderDTO> getAll() {
        return orderRepository.findAll()
                .stream().map(o -> new OrderDTO(
                        o.getId(),
                        o.getCustomer().getId(),
                        o.getPrice(),
                        o.getDate(),
                        o.getStatus().name()))
                .collect(Collectors.toList());
    }

    /**
     * Gets all orders with a given status.
     *
     * @param status     the status to filter by
     * @return all orders that have a given status
     */
    public List<OrderDTO> getAllFilter(String status) {
        logger.info("Filter by status {}", status);
        return getAll().stream().filter(o -> o.getStatus().equals(status)).collect(Collectors.toList());
    }

    /**
     * Change the status of the given order to a given status.
     *
     * @param id     the order id
     * @param status the status
     */
    public void change(Integer id, String status) {
        Order order = orderRepository.findById(id);

        logger.info("Current status: {}", order.getStatus().name());
        for (Status s: Status.values()) {
            if (status.equals(s.name())) {
                order.setStatus(s);
                break;
            }
        }
        logger.info("Next status: {}", order.getStatus().name());
        orderRepository.save(order);
    }
}
