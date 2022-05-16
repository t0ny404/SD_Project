package sd.project.Repository;

import sd.project.Model.Cart;
import sd.project.Model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByOrder(Order order);
}
