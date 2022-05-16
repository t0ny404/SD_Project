package sd.project;

import sd.project.Model.Customer;
import sd.project.Model.User;
import sd.project.Model.Utils.UserType;
import sd.project.Repository.CustomerRepository;
import sd.project.Repository.UserRepository;
import sd.project.Service.DTO.RegisterDTO;
import sd.project.Service.UserService;
import sd.project.Service.Utils.InvalidRegisterException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;


@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private final UserService userService = new UserService();

    private Customer customer;
    private User user;
    private RegisterDTO registerDTO;

    @Before
    public void setup() {
        registerDTO = new RegisterDTO("aa", "aaaaaaaa", "aaaaaaaa",
                "aa", "aa@gmail.com", String.valueOf(19));

        customer = new Customer();
        customer.setEmail("aa@gmail.com");
        customer.setName("aa");
        customer.setAge(19);

        user = new User();
        user.setUsername("aa");
        user.setPassword(UserService.ENCODER.encode("aaaaaaaa"));
        user.setType(UserType.CUSTOMER);

        customer.setUser(user);
    }

    @Test
    public void successfulRegister() {
        Mockito.doReturn(Optional.empty()).when(userRepository).findByUsername("aa");
        Mockito.doReturn(null).when(userRepository).save(user);
        Mockito.doReturn(null).when(customerRepository).save(customer);

        try {
            userService.register(registerDTO);
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = InvalidRegisterException.class)
    public void userAlreadyPresentRegister() throws InvalidRegisterException {
        Mockito.doReturn(Optional.of(user)).when(userRepository).findByUsername("aa");
        Mockito.doReturn(null).when(userRepository).save(user);
        Mockito.doReturn(null).when(customerRepository).save(customer);

        userService.register(registerDTO);
    }
}
