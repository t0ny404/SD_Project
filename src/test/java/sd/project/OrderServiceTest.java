package sd.project;

import sd.project.Model.*;
import sd.project.Model.Utils.Status;
import sd.project.Repository.*;
import sd.project.Service.ActiveCart;
import sd.project.Service.DTO.IssueDTO;
import sd.project.Service.DTO.OrderDTO;
import sd.project.Service.OrderService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;


@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private final OrderService orderService = new OrderService();

    @Captor
    ArgumentCaptor<Order> orderC;
    @Captor
    ArgumentCaptor<Cart> cartC;

    private Customer customer;
    private Order order;
    private Cart cart;

    @Before
    public void setup() {
        Issue issue1 = new Issue();
        issue1.setTitle("aa");
        issue1.setPrice(2);
        issue1.setId(1);
        Issue issue2 = new Issue();
        issue2.setTitle("bb");
        issue2.setPrice(3);
        issue2.setId(2);

        ActiveCart.getCart().add(new IssueDTO("aa", 2, 1, null, "aa", ""));
        ActiveCart.getCart().add( new IssueDTO("bb", 3, 2, null, "aa", ""));

        order = new Order(
                customer,
                Status.PENDING,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                5);
        order.setId(1);

        customer =  new Customer();
        customer.setId(1);
        order.setCustomer(customer);

        cart = new Cart();
        cart.setIssue(issue1);
    }

    @Test
    public void successfulOrder() {
        Mockito.doReturn(customer).when(customerRepository).findById(1);
        Mockito.doReturn(order).when(orderRepository).save(orderC.capture());

        orderService.order(1);

        assertEquals(order.getStatus(), orderC.getValue().getStatus());

        Mockito.verify(cartRepository, times(2)).save(cartC.capture());
        assertEquals(new Cart(new Issue(), order, 1), cartC.getValue());
    }

    @Test
    public void successfulGetHistory() {
        Mockito.doReturn(customer).when(customerRepository).findById(1);
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(order))).when(orderRepository)
                .findByCustomerAndStatusIn(customer, new Status[]{Status.DECLINED, Status.DELIVERED});
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(cart))).when(cartRepository).findByOrder(order);

        assertEquals(new ArrayList<>(Collections.singletonList(new OrderDTO(
                1,
                "aa",
                5,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                Status.PENDING.name()))), orderService.getHistory(1));
    }

    @Test
    public void successfulGetCurrent() {
        Mockito.doReturn(customer).when(customerRepository).findById(1);
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(order))).when(orderRepository)
                .findByCustomerAndStatusNotIn(customer, new Status[]{Status.DECLINED, Status.DELIVERED});
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(cart))).when(cartRepository).findByOrder(order);

        assertEquals(new ArrayList<>(Collections.singletonList(new OrderDTO(
                1,
                "aa",
                5,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                Status.PENDING.name()))), orderService.getCurrent(1));
    }

    @Test
    public void successfulGetAll() {
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(order)))
                .when(orderRepository).findAll();

        assertEquals(new ArrayList<>(Collections.singletonList(new OrderDTO(
                1,
                1,
                5,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                Status.PENDING.name()))), orderService.getAll());
    }

    @Test
    public void successfulGetAllFilter() {
        Mockito.doReturn(new ArrayList<>(Collections.singletonList(order)))
                .when(orderRepository).findAll();

        assertEquals(new ArrayList<>(Collections.singletonList(new OrderDTO(
                1,
                1,
                5,
                new SimpleDateFormat("dd-MM-yyyy").format(new Date()),
                Status.PENDING.name()))), orderService.getAllFilter(Status.PENDING.name()));
    }

    @Test
    public void successfulChange() {
        Mockito.doReturn(order).when(orderRepository).findById(1);

        orderService.change(1, Status.ACCEPTED.name());

        Mockito.verify(orderRepository).save(orderC.capture());
        assertEquals(Status.ACCEPTED, orderC.getValue().getStatus());
    }

    @Test
    public void wrongStatusChange() {
        Mockito.doReturn(order).when(orderRepository).findById(1);

        orderService.change(1, "no status");

        Mockito.verify(orderRepository).save(orderC.capture());
        assertEquals(Status.PENDING, orderC.getValue().getStatus());
    }
}
