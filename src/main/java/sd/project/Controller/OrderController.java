package sd.project.Controller;

import sd.project.Service.ActiveCart;
import sd.project.Service.DTO.OrderDTO;
import sd.project.Service.OrderService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path="/order")
public class OrderController {

    private final Logger logger = LogManager.getLogger();
    @Autowired
    private OrderService orderService;


    @PostMapping("order")
    public void order(@RequestBody Integer id) {
        orderService.order(id);
        logger.info("Order of {} placed", ActiveCart.getCart().getTotal());
        ActiveCart.getCart().empty();
        logger.info("Cart now empty {}", ActiveCart.getCart().getTotal());
    }

    @GetMapping("history/{user}")
    @ResponseBody
    public List<OrderDTO> getHistory(@PathVariable Integer user) {
        return orderService.getHistory(user);
    }

    @GetMapping("pending/{user}")
    @ResponseBody
    public List<OrderDTO> getPending(@PathVariable Integer user) {
        return orderService.getCurrent(user);
    }

    @GetMapping("all")
    @ResponseBody
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @GetMapping("filter/{status}")
    @ResponseBody
    public List<OrderDTO> getAll(@PathVariable String status) {
        logger.info("Filter orders by status {}", status);
        return orderService.getAllFilter(status);
    }

    @PutMapping("change/{id}/{s}")
    public void change(@PathVariable Integer id, @PathVariable String s) {
        logger.info("desired status: {}", s);
        orderService.change(id, s);
    }
}
