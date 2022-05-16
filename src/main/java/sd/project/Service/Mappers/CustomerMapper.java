package sd.project.Service.Mappers;

import sd.project.Model.Customer;
import sd.project.Model.User;
import sd.project.Model.Utils.UserType;
import sd.project.Service.DTO.RegisterDTO;
import sd.project.Service.UserService;


public class CustomerMapper implements Mapper<Customer, RegisterDTO> {

    public Customer convertFromDTO(RegisterDTO registerDTO) {
        Customer customer = new Customer();
        customer.setEmail(registerDTO.getEmail());
        customer.setName(registerDTO.getName());
        customer.setAge(Integer.parseInt(registerDTO.getAge()));

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(UserService.ENCODER.encode(registerDTO.getPassword()));
        user.setType(UserType.CUSTOMER);

        customer.setUser(user);

        return customer;
    }

    public RegisterDTO convertToDTO() {
        return null;
    }
}
