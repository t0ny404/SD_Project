package sd.project.Repository;

import sd.project.Model.Customer;
import sd.project.Model.Order;
import sd.project.Model.Utils.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerAndStatusIn(Customer customer, Status[] status);
    List<Order> findByCustomerAndStatusNotIn(Customer customer, Status[] status);
    Order findById(Integer id);
}
